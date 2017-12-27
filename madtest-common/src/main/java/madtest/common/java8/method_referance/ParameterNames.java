package madtest.common.java8.method_referance;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by qct on 2015/6/18.
 */
public class ParameterNames {

    public static void main(String[] args) throws Exception {
        Method method = ParameterNames.class.getMethod("main", String[].class);
        for (final Parameter parameter : method.getParameters()) {
            System.out.println("Parameter: " + parameter.getName());
        }
    }
}
