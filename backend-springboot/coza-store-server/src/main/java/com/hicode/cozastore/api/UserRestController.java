package com.hicode.cozastore.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hicode.cozastore.collection.Role;
import com.hicode.cozastore.collection.User;
import com.hicode.cozastore.exception.ApiExceptionHandler;
import com.hicode.cozastore.service.RoleService;
import com.hicode.cozastore.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserRestController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/admin/users")
    public ResponseEntity<?> fetchAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/admin/users/{id}")
    public ResponseEntity<?> fetchOne(@PathVariable("id") String id) {
        if (userService.findById(id) == null) {
            throw new ApiExceptionHandler.NotFoundException("User not found");
        } else {
            return ResponseEntity.ok(userService.findById(id));
        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> insert(@RequestBody User user) {
        log.info("User:{}", user);
        if (userService.findByUsername(user.getUsername()) != null) {
            throw new ApiExceptionHandler.BadRequestException("User already exits");
        } else {
            user.getRoles().add(roleService.findByName("ROLE_USER"));
            return ResponseEntity.ok(userService.insert(user));
        }
    }

    @PostMapping("/admin/users")
    public ResponseEntity<?> insertUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            throw new ApiExceptionHandler.BadRequestException("User already exits");
        } else {
            return ResponseEntity.ok(userService.insert(user));
        }
    }

    @PutMapping("/admin/users")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        if (userService.findByUsername(user.getId()) == null) {
            throw new ApiExceptionHandler.NotFoundException("User not found");
        } else {

            return ResponseEntity.ok(userService.insert(user));
        }
    }

    @PostMapping("user/changepassword")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> changePassword(@RequestBody PasswordRequest passwordRequest,  HttpServletResponse response ,HttpServletRequest request) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String access_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(access_token);
                String username = decodedJWT.getSubject();
                User user = userService.findByUsername(username);

                if (passwordEncoder.matches(passwordRequest.getCurrentPassword(),user.getPassword())) {
                    user.setPassword(passwordRequest.getNewPassword());
                    userService.update(user);
                    return ResponseEntity.ok("Change sassword successfully");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Current password is not correct");
                }


            } catch (Exception exception) {
                Map<String, String> errors = new HashMap<>();
                errors.put("error_message", exception.getMessage());
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errors);
            }
        } else {
            throw new ApiExceptionHandler.BadRequestException("Refresh token is missing");
        }


    }

    @GetMapping("/user/myprofile")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String access_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(access_token);
                String username = decodedJWT.getSubject();
                User user = userService.findByUsername(username);
                Map<String, Object> profile = new HashMap<>();
                profile.put("fullname", user.getFullname());
                profile.put("email", user.getEmail());
                profile.put("image", user.getImage());
                profile.put("address", user.getAddress());
                profile.put("phone", user.getPhone());
                profile.put("roles", user.getRoles());
                profile.put("orders", user.getOrders());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), profile);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                Map<String, String> errors = new HashMap<>();
                errors.put("error_message", exception.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), errors);

            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

}

@Data
class PasswordRequest {
    private String currentPassword;
    private String newPassword;
}
