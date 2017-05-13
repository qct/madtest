package madtest.common.pattern.bridge;

/**
 * Created by qct on 2015/7/10.
 */
public class SamsungTV implements TV {

    @Override
    public void on() {
        System.out.println("Samsung is turned on.");
    }

    @Override
    public void off() {
        System.out.println("Samsung is turned off.");
    }

    @Override
    public void switchChannel(int channel) {
        System.out.println("Samsung: channel - " + channel);
    }
}
