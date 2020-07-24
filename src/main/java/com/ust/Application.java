package com.ust;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // Logback uses the Simple Logging Facade for Java (SLF4J) as its native interface.
    // ogback-classic natively implements the SLF4J API so that you can readily switch back and forth between logback
    // // and other logging frameworks such as log4j or java.util.logging (JUL).
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {


        SpringApplication.run(Application.class, args);
        logger.info("-------------------------------> SpringApplication's run method finished here...");

    }

}
