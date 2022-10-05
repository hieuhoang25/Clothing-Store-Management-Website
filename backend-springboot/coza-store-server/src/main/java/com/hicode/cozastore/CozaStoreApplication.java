package com.hicode.cozastore;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;

@SpringBootApplication
public class CozaStoreApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(CozaStoreApplication.class, args);
        ClassPathResource serviceAccount = new ClassPathResource("image-cloud-98533-firebase-adminsdk-egb09-95daffa97d.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                .setStorageBucket("image-cloud-98533.appspot.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
