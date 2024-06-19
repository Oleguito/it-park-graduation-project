package ru.itpark.authservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
    
//    @Override
//    public void run(String... args) {
//        String folderPath = "/usr/src/mymaven/backend";
//        System.out.println(folderPath);
//        runLsCommand(folderPath);
//    }
//
//    private void runLsCommand(String folderPath) {
//        String osName = System.getProperty("os.name");
//        if (osName.startsWith("Linux")) {
//            try {
//                Process process = Runtime.getRuntime().exec("ls " + folderPath);
//                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
//                }
//                int exitCode = process.waitFor();
//                if (exitCode != 0) {
//                    System.err.println("Command execution failed with exit code: " + exitCode);
//                }
//            } catch (IOException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("This code is meant to run on Linux only.");
//        }
//    }
}
