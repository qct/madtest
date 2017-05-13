package madtest.common.lock;

/**
 * <p>ReadWriteLock类中，读锁和写锁各有一个获取锁和释放锁的方法。
 *
 * <p>读锁的实现在lockRead()中,只要没有线程拥有写锁（writers==0），且没有线程在请求写锁（writeRequests ==0），所有想获得读锁的线程都能成功获取。
 *
 * <p>写锁的实现在lockWrite()中,当一个线程想获得写锁的时候，首先会把写锁请求数加1（writeRequests++），然后再去判断是否能够真能获得写锁，当没有线程持有读锁（readers==0
 * ）,且没有线程持有写锁（writers==0）时就能获得写锁。有多少线程在请求写锁并无关系。
 *
 * <p>需要注意的是，在两个释放锁的方法（unlockRead，unlockWrite）中，都调用了notifyAll方法，而不是notify。要解释这个原因，我们可以想象下面一种情形：
 *
 * <p>如果有线程在等待获取读锁，同时又有线程在等待获取写锁。如果这时其中一个等待读锁的线程被notify方法唤醒，但因为此时仍有请求写锁的线程存在（writeRequests>0），所以被唤醒的线程会再次进入阻塞状态。然而，等待写锁的线程一个也没被唤醒，就像什么也没发生过一样（译者注：信号丢失现象）。如果用的是notifyAll方法，所有的线程都会被唤醒，然后判断能否获得其请求的锁。
 *
 * <p>用notifyAll还有一个好处。如果有多个读线程在等待读锁且没有线程在等待写锁时，调用unlockWrite()后，所有等待读锁的线程都能立马成功获取读锁 —— 而不是一次只允许一个。
 *
 * <p>Created by qct on 2016/3/7.
 */
public class ReadWriteLock {

    private int readers = 0;
    private int writers = 0;
    private int writeRequests = 0;

    public synchronized void lockRead()
        throws InterruptedException {
        while (writers > 0 || writeRequests > 0) {
            wait();
        }
        readers++;
    }

    public synchronized void unlockRead() {
        readers--;
        notifyAll();
    }

    public synchronized void lockWrite()
        throws InterruptedException {
        writeRequests++;

        while (readers > 0 || writers > 0) {
            wait();
        }
        writeRequests--;
        writers++;
    }

    public synchronized void unlockWrite()
        throws InterruptedException {
        writers--;
        notifyAll();
    }
}
