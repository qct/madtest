package pattern.observer;

/**
 * Created by qct on 2015/6/8.
 */
public interface Observer {
    /**
     * method to update the observer, used by subject
     */
    void update();

    /**
     * attach with subject to observe
     *
     * @param subject
     */
    void setSubject(Subject subject);
}
