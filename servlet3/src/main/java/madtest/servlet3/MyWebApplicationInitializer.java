package madtest.servlet3;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by alex on 2016/8/23.
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));


        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(WebConfig.class);
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", new DispatcherServlet(webContext));
        registration.setLoadOnStartup(2);
        registration.addMapping("/mvc/*");
    }
}
