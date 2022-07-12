package vn.fis.logfile.vinasoy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("vn.fis.logfile.vinasoy")
@EntityScan("vn.fis.logfile.vinasoy.model.entity")
public class LogfileVinasoyApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogfileVinasoyApplication.class, args);
	}

}
