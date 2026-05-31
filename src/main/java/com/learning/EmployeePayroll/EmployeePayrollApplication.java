package com.learning.EmployeePayroll;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmployeePayrollApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeePayrollApplication.class, args);
	}
    @Bean
    public CommandLineRunner testBeans(ApplicationContext context) {

        return args -> {

            String[] beans =
                    context.getBeanNamesForAnnotation(
                            org.springframework.stereotype.Component.class);

            for (String bean : beans) {
                System.out.println(bean);
            }
        };
    }

}
