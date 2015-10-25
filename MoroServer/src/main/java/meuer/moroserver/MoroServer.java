package meuer.moroserver;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoroServer {

    private static final Logger log = Logger.getLogger(MoroServer.class);

    public static void main(String[] args) {
        log.info("Startuji MoroServer.");
        SpringApplication.run(MoroServer.class, args);
    }

}
