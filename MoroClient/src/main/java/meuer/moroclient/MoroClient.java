package meuer.moroclient;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MoroClient {

    private static final Logger log = Logger.getLogger(MoroClient.class);

    public static void main(String[] args) {
        log.info("Startuji MoroClient");
        SpringApplication.run(MoroClient.class, args);
    }
}
