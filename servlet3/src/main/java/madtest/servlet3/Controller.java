package madtest.servlet3;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by alex on 2016/8/23.
 */
@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/hello-mvc")
    public String helloMvc() {
        System.out.println("Step into controller");
        return "index";
    }
}
