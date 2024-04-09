package com.labtech.events.security;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseAuthConfig {

  private static final Logger log = LoggerFactory.getLogger(FirebaseAuthConfig.class);

  @Bean
  @Primary
  public void firebaseInit() {
    try {
      InputStream in = new ClassPathResource("service-account.json").getInputStream();

      FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(in))
        .build();

      if (FirebaseApp.getApps().isEmpty()) {
        FirebaseApp.initializeApp(options);
      }

      log.info("--- Firebase Initialize ---");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
