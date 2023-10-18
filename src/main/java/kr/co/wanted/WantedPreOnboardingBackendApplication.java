package kr.co.wanted;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class WantedPreOnboardingBackendApplication {
    @PostConstruct
    void started() {
        // timezone KST 셋팅
        TimeZone.setDefault(TimeZone.getTimeZone("KST"));
    }
    public static void main(String[] args) {
        SpringApplication.run(WantedPreOnboardingBackendApplication.class, args);
    }

}
