package madtest.common.java8.parallel;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>Created by Damon.Q on 2017/3/11.
 */
public class ParallelTest {

    public static void main(String[] args) {
        long[] arrayOfLong = new long[20000];
        Arrays.parallelSetAll(arrayOfLong,
            index -> ThreadLocalRandom.current().nextInt(1000000));
        Arrays.stream(arrayOfLong)
            .limit(10)
            .forEach(i -> System.out.println(i + " "));
        System.out.println();

        Arrays.parallelSort(arrayOfLong);
        Arrays.stream(arrayOfLong).limit(10).forEach(
            i -> System.out.println(i + " ")
        );
        System.out.println();
    }
}
