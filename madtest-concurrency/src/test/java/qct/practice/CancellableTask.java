package qct.practice;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * <p>Created by qdd on 2022/6/11.
 */
public interface CancellableTask<T> extends Callable<T> {

    void cancel();

    RunnableFuture<T> newTask();
}

@ThreadSafe
class CancellingExecutor extends ThreadPoolExecutor {

    public CancellingExecutor(
            int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if (callable instanceof CancellableTask) {
            return ((CancellableTask<T>) callable).newTask();
        } else {
            return super.newTaskFor(callable);
        }
    }
}

abstract class SocketUsingTask<T> implements CancellableTask<T> {

    @GuardedBy("this")
    private Socket socket;

    protected synchronized void setSocket(Socket s) {
        socket = s;
    }

    @Override
    public void cancel() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ignored) {
        }
    }

    @Override
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketUsingTask.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }
}

enum Tsdf {
    A;

    public synchronized void testdfd() {}
}
