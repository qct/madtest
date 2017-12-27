package madtest.common.mq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.sound.midi.Soundbank;

/**
 * <p>Created by qct on 2017/4/8.
 */
public class BlockingQueueTest {

    public static void main(String[] args) {
        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);
        for(int i=0; i< 3; i++) {
            new Thread(){
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep((long) Math.random()*1000);
                            System.out.println(Thread.currentThread().getName() + " ready to put");
                            queue.put(1);
                            System.out.println(Thread.currentThread().getName() + " put data, queue has " + queue.size());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }

        new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000l);
                        System.out.println(Thread.currentThread().getName() + " ready to take");
                        queue.take();
                        System.out.println(Thread.currentThread().getName() + " taken, queue has " + queue.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
