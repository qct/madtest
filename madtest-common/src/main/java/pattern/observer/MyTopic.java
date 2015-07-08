package pattern.observer;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by qct on 2015/6/8.
 */
public class MyTopic implements Subject {

    private List<Observer> observers;
    private String message;
    private boolean changed = false;
    private final Object MUTEX = new Object();

    public MyTopic() {
        this.observers = Lists.newArrayList();
    }

    @Override
    public synchronized void register(Observer observer) {
        if(observer == null) throw new NullPointerException("Null Observer");
        if(!observers.contains(observer)) observers.add(observer);
    }

    @Override
    public synchronized void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        List<Observer> observerLocal = null;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if(!changed) return;
            observerLocal = Lists.newArrayList(observers);
            this.changed = false;
        }
        for(Observer observer : observerLocal) {
            observer.update();
        }
    }

    @Override
    public Object getUpdate(Observer observer) {
        return this.message;
    }

    /**
     * method to post message to the topic
     * @param msg
     */
    public void postMessage(String msg) {
        System.out.println("Message Posted to Topic:" + msg);
        this.message = msg;
        this.changed = true;
        notifyObservers();
    }
}
