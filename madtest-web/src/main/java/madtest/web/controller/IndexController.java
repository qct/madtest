package madtest.web.controller;

import com.google.common.collect.Maps;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
}
