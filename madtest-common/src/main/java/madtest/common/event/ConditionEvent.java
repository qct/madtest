package madtest.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by quchentao on 16/10/2.
 */
public class ConditionEvent extends ApplicationEvent {

    private boolean awesome;

    public ConditionEvent(final String source) {
        super(source);
    }

    public boolean isAwesome() {
        return awesome;
    }

    public void setAwesome(boolean awesome) {
        this.awesome = awesome;
    }
}
