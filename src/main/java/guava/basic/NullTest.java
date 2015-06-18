package guava.basic;


import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qct on 2015/5/23.
 */
public class NullTest {

    static Logger logger = LoggerFactory.getLogger(NullTest.class);

    public static void main(String[] args) throws Exception {
//        Optional<Integer> possible = Optional.of(5);
//        logger.debug("isPresent:{}", possible.isPresent());
//        logger.debug("possible:{}", possible.get());
//        testNull();
//        testNullObject();
//        sayHello(null);

        testOptional();
    }

    public static void testNull() {
        int age = 0;
        logger.debug("age:{}", age);

        long money = 0;
        logger.debug("money:{}", money);
    }

    public static void testNullObject() {
        if (null instanceof java.lang.Object) {
            logger.debug("null属于java.lang.Object类型");
        } else {
            logger.debug("null不属于java.lang.Object类型");
        }
    }

    //文艺青年 say hello
    public static void sayHello(String name) {
        name = Optional.fromNullable(name).or("火星人");
        logger.debug("文艺青年说：Hello,{}", name);
    }


    public static void testOptional() throws Exception {
        Optional<Integer> possible = Optional.of(6);
        Optional<Integer> absentOpt = Optional.absent();
        Optional<Integer> NullableOpt = Optional.fromNullable(null);
        Optional<Integer> NoNullableOpt = Optional.fromNullable(10);
        if (possible.isPresent()) {
            System.out.println("possible isPresent:" + possible.isPresent());
            System.out.println("possible value:" + possible.get());
        }
        if (absentOpt.isPresent()) {
            System.out.println("absentOpt isPresent:" + absentOpt.isPresent());
            ;
        }
        if (NullableOpt.isPresent()) {
            System.out.println("fromNullableOpt isPresent:" + NullableOpt.isPresent());
            ;
        }
        if (NoNullableOpt.isPresent()) {
            System.out.println("NoNullableOpt isPresent:" + NoNullableOpt.isPresent());
            ;
        }
    }
}
