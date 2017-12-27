package qct;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
public class ConcurrencyApplication {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrencyApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // fetch all customers
            logger.info("Customers found with findAll():");
            logger.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                logger.info(customer.toString());
            }
            logger.info("");

            // fetch an individual customer by ID
            Optional<Customer> customer = repository.findById(1L);
            logger.info("Customer found with findOne(1L):");
            logger.info("--------------------------------");

            logger.info(customer.orElse(new Customer()).toString());
            logger.info("");

            // fetch customers by last name
            logger.info("Customer found with findByLastName('Bauer'):");
            logger.info("--------------------------------------------");
            for (Customer bauer : repository.findByLastName("Bauer")) {
                logger.info(bauer.toString());
            }
            logger.info("");
        };
    }*/
}
