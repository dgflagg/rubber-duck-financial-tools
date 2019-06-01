package info.dgflagg.rubberduck.financialtools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
	    log.info("starting the Rubber Duck Financial Tools app...");
		SpringApplication.run(Application.class, args);
	}

}
