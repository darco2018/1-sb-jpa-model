package com.ust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

		Book book1 = new Book();
		book1.setName("Jungle");
		System.out.println(book1.hashCode()); // 1902216702 >> 961
		System.out.println(book1);

		Book book2 = new Book();
		book2.setName("Different Jungle");
		System.out.println(book2.hashCode()); // 1653309853 >> 961
		System.out.println(book2);
	}

}
