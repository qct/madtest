package madtest.common.pattern.bridge;

/**
 * Created by qct on 2015/7/10.
 */
public class AbstractRemoteControl {

    private TV tv;

    public AbstractRemoteControl(TV tv) {
        this.tv = tv;
    }

    public void turnOn() {
        tv.on();
    }

    public void turnOff() {
        tv.off();
    }

    public void setChannel(int channel) {
        tv.switchChannel(channel);
    }
}
