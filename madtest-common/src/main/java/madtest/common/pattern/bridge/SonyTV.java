package madtest.common.pattern.bridge;

/**
 * Created by qct on 2015/7/10.
 */
public class SonyTV implements TV {

    @Override
    public void on() {
        System.out.println("Sony is turned on.");
    }

    @Override
    public void off() {
        System.out.println("Sony is turned off.");
    }

    @Override
    public void switchChannel(int channel) {
        System.out.println("Sony: channel - " + channel);
    }
}
