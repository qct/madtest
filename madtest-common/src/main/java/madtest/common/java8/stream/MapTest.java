package madtest.common.java8.stream;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * <p>Created by Damon.Q on 2017/3/11.
 */
public class MapTest {

    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
        Stream<Double> doubleStream = stream.flatMap(new Function<Integer, Stream<Double>>() {
            @Override
            public Stream<Double> apply(Integer integer) {
                return Stream.of(Double.valueOf(integer));
            }
        });
        doubleStream.forEach(x -> System.out.println(x));
    }
}
