//package com.mayo.crud.config;
//
//import javax.annotation.PostConstruct;
//import org.springframework.context.annotation.Configuration;
//
//import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
//@Configuration
//public class SecureRandomConfig {
//
//
//    @PostConstruct
//    public void init() {
//        try {
//            SecureRandom secureRandom = SecureRandom.getInstance("NativePRNG");
//            SecureRandom.setSeed(secureRandom.generateSeed(20)); // Initialize with a seed
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace(); // Handle exception
//        }
//    }
//}
//
