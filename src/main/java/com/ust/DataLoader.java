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

@Component
public class DataLoader implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private UserRepository userRepository;

    @Override
  public void run(String ...args) throws Exception {
        // will work but breaks tests
       //userRepository.save(new User("darek@zoho.com", "darek987", "ADMIN", "darek"));
       logger.info("App Runner ---------------------> App Runner ----------------------------App Runner ");
   }
}
