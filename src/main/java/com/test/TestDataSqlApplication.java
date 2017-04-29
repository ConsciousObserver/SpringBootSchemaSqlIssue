package com.test;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class TestDataSqlApplication {
	
	@Autowired
	private UserDetailsRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TestDataSqlApplication.class, args);
	}
	
	@PostConstruct
	private void init() throws Exception {
		System.out.println("< CustomLog> ################################################################");
		System.out.println("Row count : " + userRepository.count());
		System.out.println("Rows : " + new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString( userRepository.findAll()));
		System.out.println("< /CustomLog> ################################################################");
	}
}

@Entity
@Table(name="USER_DETAILS")
class UserDetails {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
	
}