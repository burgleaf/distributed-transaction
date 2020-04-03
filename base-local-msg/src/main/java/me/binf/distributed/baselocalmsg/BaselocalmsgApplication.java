package me.binf.distributed.baselocalmsg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BaselocalmsgApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaselocalmsgApplication.class, args);
	}

}
