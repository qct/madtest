package madtest.common.encryption;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

/**
 * Created by Damon.Q on 2017/1/17.
 */
public class RSAUtil {

    public static final String KEY_ALGORITHM = "RSA";//
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    public static final String PUBLIC_KEY = "RSAPublicKey";//公钥
    public static final String PRIVATE_KEY = "RSAPrivateKey";//私钥
    public static final int KEY_SIZE = 512;


    /**
     * <p>初始化密钥对.</p>
     *
     * <p>从代码中可以看出密钥的初始化长度为1024位，密钥的长度越长，安全性就越好，但是加密解密所用的时间就会越多。
     * 而一次能加密的密文长度也与密钥的长度成正比。
     * 一次能加密的密文长度为：密钥的长度/8-11。
     * 所以1024bit长度的密钥一次可以加密的密文为1024/8-11=117bit。
     * 所以非对称加密一般都用于加密对称加密算法的密钥，而不是直接加密内容。
     * 对于小文件可以使用RSA加密，但加密过程仍可能会使用分段加密。</p>
     */
    public static Map<String, Key> initKey() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Key> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);

        return keyMap;
    }

    /**
     * 取得公钥，并转化为String类型.
     *
     * @param keyMap keyMap
     * @return public key
     * @throws Exception Exception
     */
    public static String getPublicKey(Map<String, Key> keyMap) throws Exception {
        Key key = keyMap.get(PUBLIC_KEY);
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    /**
     * 取得私钥，并转化为String类型.
     *
     * @param keyMap key map
     * @return private key
     * @throws Exception Exception
     */
    public static String getPrivateKey(Map<String, Key> keyMap) throws Exception {
        Key key = keyMap.get(PRIVATE_KEY);
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    /**
     * 用私钥加密.
     *
     * @param data 加密数据
     * @param key 密钥
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
        //解密密钥
        byte[] keyBytes = Base64.getDecoder().decode(key);
        //取私钥
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        //对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 用私钥解密.
     *
     * @param data 加密数据
     * @param key 密钥
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
        //对私钥解密
        byte[] keyBytes = Base64.getDecoder().decode(key);

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        //对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 用公钥加密.
     *
     * @param data 加密数据
     * @param key 密钥
     */
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
        //对公钥解密
        byte[] keyBytes = Base64.getDecoder().decode(key);
        //取公钥
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        //对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 用公钥解密.
     *
     * @param data 加密数据
     * @param key 密钥
     */
    public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
        //对私钥解密
        byte[] keyBytes = Base64.getDecoder().decode(key);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        //对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 用私钥对信息生成数字签名.
     *
     * @param data 加密数据
     * @param privateKey 私钥
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        //解密私钥
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        //构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        //指定加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //取私钥匙对象
        PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        //用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey2);
        signature.update(data);

        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * 用私钥对信息生成数字签名, 默认utf8编码.
     *
     * @param data 加密数据
     * @param privateKey 私钥
     */
    public static String sign(String data, String privateKey) throws Exception {
        return sign(data.getBytes(StandardCharsets.UTF_8), privateKey);
    }

    /**
     * 校验数字签名.
     *
     * @param data 加密数据
     * @param publicKey 公钥
     * @param sign 数字签名
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        //解密公钥
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        //构造X509EncodedKeySpec对象
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        //指定加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //取公钥匙对象
        PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey2);
        signature.update(data);
        //验证签名是否正常
        return signature.verify(Base64.getDecoder().decode(sign));
    }

    /**
     * 校验数字签名，默认utf8编码.
     *
     * @param data 加密数据
     * @param publicKey 公钥
     * @param sign 数字签名
     */
    public static boolean verify(String data, String publicKey, String sign) throws Exception {
        return verify(data.getBytes(StandardCharsets.UTF_8), publicKey, sign);
    }

    public static void main(String[] args) throws Exception {
        Map<String, Key> keyPair = initKey();
        String publicKey = getPublicKey(keyPair);
        String privateKey = getPrivateKey(keyPair);
        System.out.println("public key: " + publicKey);
        System.out.println("private key: " + privateKey);

        publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJtJaIRDREqH4fxPxOGoDyZJq8+b8B98lfZcUxJUyKz/"
            + "KwvUuHKdpdONnbJ6T1ZSvPVu+4SJssJ/AHOuyO52tEMCAwEAAQ==";
        privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAm0lohENESofh/E/E4agPJkmrz5v"
            + "wH3yV9lxTElTIrP8rC9S4cp2l042dsnpPVlK89W77hImywn8Ac67I7na0QwIDAQABAkAHf9MRWCJLKybJx"
            + "ZxvfWtF4RbwDsrY6m5fmHPWlcufNxw7UPVYZDSPx/Uj+o1HwkfO8YBI0CbCVcBckkQ6J3o5AiEAzh36hMz"
            + "B//fI6pvOFakiiiJhc0FAaP/EFtrQfu3SRicCIQDA3jnqa0DB7TKMG3hGwTPK3pgmcqZCXF6lyig75QDuh"
            + "QIgcYh7zJiIp7jR18C4lJ/UYagsa3LeyrdPSJOA0tmmwHMCIFAsULD0sq+qlWeOh7oJ2CjNzTu2Twyo7yN"
            + "F3OHA+VktAiEAiHy5hKypxqVzwMLkdVVX7+4ZfPIu43P6EKqIllFvFoE=";

        String dotNetPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYTCGxousnbDKuRFQxwsXW28b"
            + "TcqvGDGT/BSelSUjgHGrsHjD8s0AwJd251rUHse7hrqvFIXAH+DZZRjP73UZ1n76VkWlOzboqfGpobBPRx"
            + "K0b4E0GCatK2rObPOfau1UH6TaPG8H6i4n/ornMbenYBiNYf+gMUVRL1LcVBngrOwIDAQAB";
        String dotNetPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJhMIbGi6ydsMq"
            + "5EVDHCxdbbxtNyq8YMZP8FJ6VJSOAcauweMPyzQDAl3bnWtQex7uGuq8UhcAf4NllGM/vdRnWfvpWRaU7N"
            + "uip8amhsE9HErRvgTQYJq0ras5s859q7VQfpNo8bwfqLif+iucxt6dgGI1h/6AxRVEvUtxUGeCs7AgMBAA"
            + "ECgYABGyxzeddzZdnIYj6qTdvZ/6amCoMs4RIquALdtHaPCiXMqTt52mNw9hVyMBkIgUZW5UniGKgTpr3e"
            + "UCA4iBbm6O5HHPAdZ95qT9RbYncBEzOk8IiYyJfhT+wdljhk9wiClmxWf4ebTXUgVF8sA8nGkZB2nfoM8V"
            + "POXKOr0QI74QJBANK6XT1wH2fH4P+YJSQ3R5lgiPbJI9ElTHv3PhaYID9jgWzVgLatFw7NVMJoFdGlGZ7S"
            + "IrYi3mlGoTWBmLkeOQ0CQQC5BDJLyJjRmxBY90HDWY9vwKp6+X6fbU4SrH5QRWkLSNr1bXfguQMvD5sDAe"
            + "imD9iS3PHJaUGaNI5qfDtmEFNnAkBBeNnDXvTC/90uTI0tpWc/hjjHxFK2hoou8fB+gKF0UGxD7knheAHz"
            + "/WPaSxCMZGmDaPKT+F12BBXOVAepbHR5AkA8UK7o38NaCbghmLG6spa9Ms0g+/cYmDSsh8lB9+7Pu/eNrR"
            + "s4ingMPoUlTNkAUggjkJ3OMF8vm1SWSFvA4S+fAkEAh64NaBVpx8JMNcpTnQ2G9YBpnSuzYwFpQq7s0QD+"
            + "IrOPvcwLqRdfVsqxpxP+ps9iqkLbAWvRBSogTwDi2V6SgQ==";
        // sign
        String ss = "你好，世界";
        String sign = sign(ss.getBytes(StandardCharsets.UTF_8), dotNetPrivateKey);
        System.out.println("sign: " + sign);

        // verify
        boolean verify = verify(ss.getBytes(StandardCharsets.UTF_8), dotNetPublicKey, sign);
        System.out.println("verify result: " + verify);
    }
}
