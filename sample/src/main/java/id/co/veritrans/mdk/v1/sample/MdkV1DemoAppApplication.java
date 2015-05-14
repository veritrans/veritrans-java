package id.co.veritrans.mdk.v1.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.ManagementSecurityAutoConfiguration.class})
public class MdkV1DemoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdkV1DemoAppApplication.class, args);
    }
}
