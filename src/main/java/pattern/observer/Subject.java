package pattern.observer;

/**
 * Created by qct on 2015/6/8.
 */
public interface Subject {

    /**
     * methods to register observers
     *
     * @param observer
     */
    void register(Observer observer);

    /**
     * methods to unregister observers
     *
     * @param observer
     */
    void unregister(Observer observer);

    /**
     * method to notify observers of change
     */
    void notifyObservers();

    /**
     * method to get updates from subject
     *
     * @param observer
     * @return
     */
    Object getUpdate(Observer observer);
}
