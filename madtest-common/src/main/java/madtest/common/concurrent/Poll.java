package madtest.common.concurrent;

import com.google.common.collect.Lists;
import java.util.Comparator;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Created by Damon.Q on 2017/10/9.
 */
public class Poll {

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(6);
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        List<List<Integer>> results = Lists.newArrayList();
        for (int i = 0; i < 6; i++) {
            results.add(Lists.newArrayList());
            executorService.submit(new Poller(cb, results.get(i)));
        }
        ExecutorServiceUtil.shutdownAndAwaitTermination(executorService);

        Stream<LongSummaryStatistics> stat = results.stream()
            .map(integers -> integers.stream().collect(Collectors.summarizingLong(x -> x)));
        Optional<LongSummaryStatistics> collect = stat.max(Comparator.comparingLong(LongSummaryStatistics::getSum));
        collect.ifPresent(System.out::println);
    }
}

class Poller implements Callable<List<Integer>> {

    private final CyclicBarrier cb;
    private final List<Integer> results;

    Poller(CyclicBarrier cb, List<Integer> results) {
        this.cb = cb;
        this.results = results;
    }

    @Override
    public List<Integer> call() throws Exception {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(new Random().nextInt(10));
                int result = new Random().nextInt(10);
                results.add(result);
                System.out.println(Thread.currentThread().getName() + ", round " + (i + 1) + " : " + result);
                cb.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}