package madtest.web2;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.concurrent.Callable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qct on 2015/7/8.
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Map hello() {
        Map<String, String> result = Maps.newHashMap();
        result.put("name", "哼哼哼");
        return result;
    }

    @RequestMapping("/api")
    public Callable<User> api() {
        System.out.println("========api.......");
        return new Callable<User>() {
            @Override
            public User call() throws Exception {
                Thread.sleep(2L * 1000);
                User user = new User();
                user.setName("Oscar");
                return user;
            }
        };
    }
}
