package madtest.common.pattern.observer;

/**
 * Created by qct on 2015/6/8.
 */
public interface Subject {

    /**
     * methods to register observers
     */
    void register(Observer observer);

    /**
     * methods to unregister observers
     */
    void unregister(Observer observer);

    /**
     * method to notify observers of change
     */
    void notifyObservers();

    /**
     * method to get updates from subject
     */
    Object getUpdate(Observer observer);
}
