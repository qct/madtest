package madtest.servlet3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by alex on 2016/8/23.
 */
@Configuration
@ComponentScan(basePackages = "madtest.servlet3")
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("${key1}")
    private String key1;

    @Bean
    public BeanOne haha() {
        BeanOne beanOne = new BeanOne();
        beanOne.setName(key1);
        return beanOne;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer loadProperties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        return configurer;
    }
}
