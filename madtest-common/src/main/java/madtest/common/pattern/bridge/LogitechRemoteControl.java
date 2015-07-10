package madtest.common.pattern.bridge;

/**
 * Created by qct on 2015/7/10.
 */
public class LogitechRemoteControl extends AbstractRemoteControl {
    public LogitechRemoteControl(TV tv) {
        super(tv);
    }

    public void setChannelKeyboard(int channel) {
        setChannel(channel);
        System.out.println("Logitech use keyword to set channel.");
    }
}
