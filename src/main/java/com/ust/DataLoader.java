package com.ust;

import com.ust.user.User;
import com.ust.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private UserRepository userRepository;

    @Override
  public void run(String ...args) throws Exception {
        // will work but breaks tests
       userRepository.save(new User("CLuser@zoho.com", "CLuser987", "ADMIN", "CLuser"));
       logger.info("App Runner ---------------------> App Runner ----------------------------App Runner ");

       // Arrays.asList(args).forEach(arg -> System.out.println(arg));
   }
}
