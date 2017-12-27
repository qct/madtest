package madtest.common.pattern.bridge;

/**
 * Created by qct on 2015/7/10.
 */
public class BridgeTest {

    public static void main(String[] args) {
        TV sonyTV = new SonyTV();
        LogitechRemoteControl lrc1 = new LogitechRemoteControl(sonyTV);
        lrc1.setChannelKeyboard(100);
        GigabyteRemoteControl grc1 = new GigabyteRemoteControl(sonyTV);
        grc1.setChannel(90);

        TV samsungTV = new SamsungTV();
        LogitechRemoteControl lrc2 = new LogitechRemoteControl(samsungTV);
        lrc2.setChannelKeyboard(1);
        GigabyteRemoteControl grc2 = new GigabyteRemoteControl(samsungTV);
        grc2.setChannel(2);
    }
}
