package qct;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConcurrencyApplication implements CommandLineRunner {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone
        (ZoneId.systemDefault());

    //    @Value("#{new java.text.SimpleDateFormat('yyyy-MM-dd HH:mm:ss').parse('${aa}')}")
    @Value("${aa}")
    private String startTime;

    @Value("${my-debug: false}")
    private boolean debug = false;

    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //        ZonedDateTime parse = ZonedDateTime.parse(startTime, FORMATTER);
        //        System.out.println(parse);
        //        System.out.println(parse.toEpochSecond());
        if (debug) {
            ZonedDateTime parse = ZonedDateTime.parse(startTime, FORMATTER);
            System.out.println(parse);
            System.out.println(parse.toEpochSecond());
        }else {
            System.out.println("not a debug");
        }
    }
}
