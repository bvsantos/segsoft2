package fct.unl.pt.csdw1;

import fct.unl.pt.csdw1.Services.OurService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SegsoftApplication {

	public static void main(String[] args) {
		SpringApplication.run(SegsoftApplication.class, args);
	}

}
