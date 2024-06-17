package com.example.demo.load;

import com.example.demo.PingApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.jar.Manifest;

import static java.lang.System.out;

/**
 * @author mirco.cennamo on 17/06/2024
 * @project ping
 */
@Configuration
public class Load {
    private static final String NO_MANIFEST_DETAILS_MESSAGE = "No manifest file found. Did you build the package using Maven?";
    @Bean
    CommandLineRunner initDatabase() {

        return args -> {
            out.println("Hello! Application release details:");
            out.println(getManifest());
        };
    }


    private static String getManifest() throws Exception {
        try (InputStream manifestStream = PingApplication.class.getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF")) {
            if (manifestStream != null) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                new Manifest(manifestStream).write(out);
                String manifestDetails = out.toString();
                return manifestDetails.isBlank() ? NO_MANIFEST_DETAILS_MESSAGE : manifestDetails;
            }
        }

        return NO_MANIFEST_DETAILS_MESSAGE;
    }
}
