package madtest.common.queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <p>Created by qct on 2017/5/6.
 */
public class PeekPollTest {

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayDeque<>(10);

        new Thread(() -> {
            while (true) {
                Integer peek = queue.peek();
                if(peek == null) {
                    for (int i=0;i<20;i++) {
                        boolean offer = queue.offer(i);
                        System.out.println("insert successfully, " + i);
                    }
                }else {
                    Integer poll = queue.poll();
                    if(poll != null) {
                        System.out.println("do something... : " + poll);
                    }else {
                        System.out.println("queue is empty, waiting for insert...");
                    }
                }

                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
