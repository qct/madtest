package madtest.common.java8.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Created by Damon.Q on 2017/3/11.
 */
public class Streams {

    public static void main(String[] args) {
        final Collection<Task> tasks = Arrays.asList(
            new Task(Status.OPEN, 5),
            new Task(Status.OPEN, 9),
            new Task(Status.CLOSED, 13)
        );

        // Calculate total points of all active tasks using sum()
        final long totalPointsOfOpenTasks = tasks
            .stream()
            .filter(task -> task.getStatus() == Status.OPEN)
            .mapToInt(Task::getPoints)
            .sum();
        System.out.println("Total points: " + totalPointsOfOpenTasks);

        // Calculate total points of all tasks
        final double totalPoints = tasks
            .stream()
            .parallel()
            .map(Task::getPoints)
            .reduce(0, Integer::sum);
        System.out.println("Total points(all tasks): " + totalPoints);

        // Group tasks by their status
        final Map<Status, List<Task>> map = tasks
            .stream()
            .collect(Collectors.groupingBy(Task::getStatus));
        System.out.println(map);

        // Calculate the weight of each tasks (as percent of total points)
        final Collection<String> result = tasks
            .stream()                                        // Stream< String >
            .mapToInt(Task::getPoints)                     // IntStream
            .asLongStream()                                  // LongStream
            .mapToDouble(points -> points / totalPoints)   // DoubleStream
            .boxed()                                         // Stream< Double >
            .mapToLong(weight -> (long) (weight * 100)) // LongStream
            .mapToObj(percentage -> percentage + "%")      // Stream< String>
            .collect(Collectors.toList());                 // List< String >

        System.out.println(result);
    }

    private enum Status {
        OPEN, CLOSED
    }

    private static final class Task {

        private final Status status;
        private final Integer points;

        Task(Status status, Integer points) {
            this.status = status;
            this.points = points;
        }

        public Status getStatus() {
            return status;
        }

        public Integer getPoints() {
            return points;
        }

        @Override
        public String toString() {
            return "Task{" +
                "status=" + status +
                ", points=" + points +
                '}';
        }
    }
}
