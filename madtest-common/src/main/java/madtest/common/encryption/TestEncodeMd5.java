package madtest.common.encryption;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.UnixCrypt;
import org.apache.commons.codec.net.URLCodec;
import org.apache.log4j.Logger;

/**
 * Created by qct on 2015/1/23.
 */
public class TestEncodeMd5 {
    static Logger logger = Logger.getLogger(TestEncodeMd5.class);

    public static void main(String[] args) throws EncoderException {
        String pwd = "123";
        logger.debug(org.springframework.util.DigestUtils.md5DigestAsHex(pwd.getBytes()));
        logger.debug(DigestUtils.md5Hex(pwd.getBytes()));
        logger.debug(UnixCrypt.crypt(pwd.getBytes()));//DES
        URLCodec urlCodec = new URLCodec();
        logger.debug(urlCodec.encode("http://www.baidu.com"));

    }

}
