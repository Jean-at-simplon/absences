package fr.simplon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Config extends WebMvcConfigurerAdapter{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
}
