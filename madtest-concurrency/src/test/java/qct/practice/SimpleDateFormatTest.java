package qct.practice;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


/**
 * <p>Created by qdd on 2022/6/5.
 */
@Slf4j
public class SimpleDateFormatTest {

    @Test
    void name() {
        SimpleDateFormatContainer container = new SimpleDateFormatContainer(); // 6/5/22 4:08 PM
        Date date = new Date();
        new Thread(() -> {
                    for (int i = 0; i < 10; i++) {
                        String s = container.getSdf().format(date);
                        log.info("{}", s);
                    }
                })
                .start();

        new Thread(() -> container.sdf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))).start();
    }

    static class SimpleDateFormatContainer {

        private SimpleDateFormat sdf = new SimpleDateFormat();

        public SimpleDateFormat getSdf() {
            return sdf;
        }

        public SimpleDateFormatContainer sdf(SimpleDateFormat sdf) {
            this.sdf = sdf;
            return this;
        }
    }
}
