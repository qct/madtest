package madtest.common.pattern.bridge;

/**
 * Created by qct on 2015/7/10.
 */
public class GigabyteRemoteControl extends AbstractRemoteControl {

    public GigabyteRemoteControl(TV tv) {
        super(tv);
    }

    @Override
    public void setChannel(int channel) {
        super.setChannel(channel);
        System.out.println("Gigabyte use keyword to set channel.");
    }
}
