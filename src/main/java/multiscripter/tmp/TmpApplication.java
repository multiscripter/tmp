package multiscripter.tmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "multiscripter.tmp.*")
@SpringBootApplication
@EntityScan("multiscripter.tmp.models")
public class TmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmpApplication.class, args);
    }
}
