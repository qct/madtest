package java8.lambda;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by qct on 2015/5/17.
 */
public class LambdaTest {
    public static void main(String[] args) {

        //文艺青年写法：
        List<String> names = new ArrayList<>();
        names.add("TaoBao");
        names.add("ZhiFuBao");
        List<String> lowercaseNames = FluentIterable.from(names).transform(new Function<String, String>() {
            @Override
            public String apply(String name) {
                return name.toLowerCase();
            }
        }).toList();

        for (String s : lowercaseNames) {
            System.out.println(s);
        }

        //lambda  表达式写法
        List<String> lowercaseNames2 = names.stream().map((name) -> name.toLowerCase()).collect(Collectors.toList());
        List<String> lowercaseNames3 = names.stream().map(String::toLowerCase).collect(Collectors.toList());
    }
}
