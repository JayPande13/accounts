package com.jay.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl") // this is the bean name
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts Mircoservice Documentation",
                description = "Banks Accounts REST API V1 Documentation",
                contact = @Contact(
                        name = "Jay Pande",
                        email = "jmaypande@gmail.com"
                )
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
