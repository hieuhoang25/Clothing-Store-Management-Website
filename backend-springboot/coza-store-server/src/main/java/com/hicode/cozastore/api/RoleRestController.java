package com.hicode.cozastore.api;

import com.hicode.cozastore.collection.Role;
import com.hicode.cozastore.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@CrossOrigin("*")
public class RoleRestController {
    private final RoleService roleService;

    @GetMapping("roles/{id}")
    private ResponseEntity<?> getRole(@PathVariable("id") String id){
        if (roleService.findById(id)==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found!");
        }
        return ResponseEntity.ok(roleService.findById(id));
    }
    @PostMapping("admin/roles")
    private ResponseEntity<?> addRole(@RequestBody Role role){
        if (roleService.findByName(role.getName())!=null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role already exists!");
        }
        return ResponseEntity.ok(roleService.insert(role));
    }
    @PutMapping("admin/roles")
    private ResponseEntity<?> updateRole(@RequestBody Role role){
        if (roleService.findById(role.getId())==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
        }
        return ResponseEntity.ok(roleService.update(role));
    }
    @DeleteMapping("admin/roles/{id}")
    private ResponseEntity<?> deleteRole(@PathVariable("id")String id){
        if (roleService.findById(id)==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
        }
        roleService.delete(roleService.findById(id));
        return ResponseEntity.ok().build();
    }
}
