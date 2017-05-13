package madtest.common.java8.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import madtest.common.java8.function.Student;

/**
 * <p>Created by Damon.Q on 2017/3/11.
 */
public class ReduceTest {

    public static void main(String[] args) {
        int value = Stream.of(1, 2, 3, 4).reduce(100, (sum, item) -> sum + item);
        System.out.println(value);
        int value2 = Stream.of(1, 2, 3, 4).reduce(100, Integer::sum);
        System.out.println(value2);

        Stream<Integer> stream = Stream.of(1, 2, 3, 4).filter(p -> p > 2);
//        List<Integer> result = stream
//            .collect(() -> new ArrayList<>(),
//                (list, item) -> list.add(item),
//                (one, two) -> one.addAll(two));
        List<Integer> result2 = stream.collect(ArrayList::new, List::add, List::addAll);
        System.out.println(result2);

        Stream<Student> studentStream = Stream.of(new Student("alex", "lee", 9.0));
//        studentStream.collect(HashMap::new, (map, p) -> map.put(p.getFirstName(), p.getGrade()), Map::putAll);
        Map<String, Double> collect = studentStream.collect(
            Collectors.toMap(p -> p.getFirstName(), p -> p.getGrade(), (exist, newv) -> newv));
        System.out.println(collect);
    }
}
