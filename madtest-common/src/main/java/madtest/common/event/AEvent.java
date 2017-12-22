package madtest.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by alex on 2016/9/30.
 */
public class AEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public AEvent(final String source) {
        super(source);
    }
}
