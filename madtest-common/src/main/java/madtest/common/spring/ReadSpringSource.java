package madtest.common.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by alex on 2016/10/25.
 */
public class ReadSpringSource {

    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource(
            "META-INF/spring/spring-read-source.xml");
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(
            defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        System.out.println("Numbers: " + defaultListableBeanFactory.getBeanDefinitionCount());
        ((Person) defaultListableBeanFactory.getBean("person")).work();

        ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
            "classpath:spring-read-source.xml");
        System.out.println("Numbers: " + applicationContext.getBeanDefinitionCount());
        ((Person) applicationContext.getBean("person")).work();


    }
}
