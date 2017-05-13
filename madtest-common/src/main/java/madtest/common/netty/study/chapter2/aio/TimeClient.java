package madtest.common.netty.study.chapter2.aio;

/**
 * Created by quchentao on 15/10/26.
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                //
            }
        }
        new Thread(new AsynctimeClientHandler("127.0.0.1", port), "AIO-AsyncTimeClientHandler-001")
            .start();
    }
}
