package madtest.common.java8.lambda;

import java.util.Arrays;

/**
 * Created by qct on 2015/6/18.
 */
public class Lambdadada {

    public static void main(String[] args) {
        Arrays.asList("a", "b", "c", "d").forEach(e -> System.out.println(e));
        System.out.println("***************************************************");
        //编译器会根据上下文来推测参数的类型，或者你也可以显示地指定参数类型，只需要将类型包在括号里。
        Arrays.asList("a", "b", "c", "d").forEach((String e) -> System.out.println(e));
        System.out.println("***************************************************");
        Arrays.asList("a", "b", "c", "d").forEach((String e) -> {
            System.out.println(e);
            System.out.println(1);
        });
        System.out.println("***************************************************");

        //Lambda表达式可能会引用类的成员或者局部变量（会被隐式地转变成final类型），下面两种写法的效果是一样的：
        String separator = ",";
        Arrays.asList("a", "b", "c", "d").forEach((String e) -> System.out.println(e + separator));
        System.out.println("***************************************************");

        final String separator2 = ",";
        Arrays.asList("a", "b", "c", "d").forEach((String e) -> System.out.println(e + separator2));
        System.out.println("***************************************************");

        //Lambda表达式可能会有返回值，编译器会根据上下文推断返回值的类型。如果lambda的语句块只有一行，不需要return关键字。
        // 下面两个写法是等价的：
        Arrays.asList("a", "b", "c", "d").sort((e1, e2) -> e1.compareTo(e2));
        Arrays.asList("a", "b", "c", "d").sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });
    }
}
