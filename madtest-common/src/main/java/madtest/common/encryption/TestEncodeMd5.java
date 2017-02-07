package madtest.common.encryption;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.UnixCrypt;
import org.apache.commons.codec.net.URLCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qct on 2015/1/23.
 */
public class TestEncodeMd5 {

    static Logger logger = LoggerFactory.getLogger(TestEncodeMd5.class);

    public static void main(String[] args) throws EncoderException {
        String pwd = "123";
        logger.debug(org.springframework.util.DigestUtils.md5DigestAsHex(pwd.getBytes()));
        logger.debug(DigestUtils.md5Hex(pwd.getBytes()));
        logger.debug(UnixCrypt.crypt(pwd.getBytes()));//DES
        URLCodec urlCodec = new URLCodec();
        logger.debug(urlCodec.encode("http://www.baidu.com"));

        /*System.out.println(
        new String(Base64.getDecoder().decode
                ("5Lya6K6u5a6kMeS4u+aMgeS6ui0mZ3Q7Jmd0OyA8YSBpZD0ibW9kZXJhdG9yVVJMIiB0YXJnZXQ9Il9ibGFuayIgaHJlZj0iaHR0cDovLzIxOC4xNy4xNjkuMTcxOjg4ODgvaHRtbC9tYXJrZXQvY2xvdWQtbWVldGluZy1qb2luLmh0bWw/YldWbGRHbHVaMGxFUFdGamJTMTRZV2czZFRSaFlpWnRaV1YwYVc1blRtRnRaVDNtb1lqbHBKcmxqNUhsajVIbm1vVGt2SnJvcnE3bHJxUXRlR0ZvTjNVMFlXSW1iV1ZsZEdsdVowdGxlVDExT0cwNWFtTnhOU1owZVhCbFBXMXZaR1Z5WVhSdmNnPT0iIHN0eWxlPSJjb2xvcjojMDA5OWQ3OyI+6L+b5YWl5Lya6K6u5a6kPC9hPjxicj7kvJrorq7lrqQx5Y+C5LiO6ICFLSZndDsmZ3Q7IDxhIGlkPSJhdHRlbmRlZVVSTCIgdGFyZ2V0PSJfYmxhbmsiIGhyZWY9Imh0dHA6Ly8yMTguMTcuMTY5LjE3MTo4ODg4L2h0bWwvbWFya2V0L2Nsb3VkLW1lZXRpbmctam9pbi5odG1sP2JXVmxkR2x1WjBsRVBXRmpiUzE0WVdnM2RUUmhZaVp0WldWMGFXNW5UbUZ0WlQzbW9ZamxwSnJsajVIbGo1SG5tb1Rrdkpyb3JxN2xycVF0ZUdGb04zVTBZV0ltYldWbGRHbHVaMHRsZVQxaU5IWTNlWEZwYkNaMGVYQmxQV0YwZEdWdVpHVmwiIHN0eWxlPSJjb2xvcjojMDA5OWQ3OyI+6L+b5YWl5Lya6K6u5a6kPC9hPg=="))
        );

        System.out.println(
        new String(Base64.getDecoder().decode
                ("bWVldGluZ0lEPWFjbS14YWg3dTRhYiZtZWV0aW5nTmFtZT3moYjlpJrlj5Hlj5HnmoTkvJrorq7lrqQteGFoN3U0YWImbWVldGluZ0tleT1iNHY3eXFpbCZ0eXBlPWF0dGVuZGVl"))
        );*/

//        System.out.println(new Date((Long) 1466697600446l));

    }
}
