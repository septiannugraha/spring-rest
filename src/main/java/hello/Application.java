package hello;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean("abc")
    public Greeting sample() {
    	return new Greeting(18, "BPKP");
    }
}
