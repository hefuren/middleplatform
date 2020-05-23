package com.bluesky.middleplatform.commons.utils.password;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * PBKDF2加密
 */
public class PBKDF2Utils {

    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    //盐的长度
    public static final int SALT_SIZE = 16;

    //生成密文的长度
    public static final int HASH_SIZE = 32;

    /**
     * 默认场景PBKDF2算法迭代次数：50000
     *
     */
    public static final int PBKDF2_DEFAULT_ITERCOUNT = 50000;

    /**
     * 性能场景PBKDF2算法迭代次数：1000
     *
     */
    public static final int PBKDF2_PERFORMANCE_ITERCOUNT = 1000;


    /**
     * 对输入的密码进行验证；
     * @param password 用户输入的密码
     * @param salt 随机盐值（加密的时候，把盐值存入数据库中）
     * @param iterCount 迭代次数：默认 PBKDF2_DEFAULT_ITERCOUNT = 50000;性能场景 PBKDF2_PERFORMANCE_ITERCOUNT = 1000
     * @param encryptedPassword 用户密文密码
     * @return 密码校验结果
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static boolean verify(String password, String salt, int iterCount, String encryptedPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 用相同的盐值对用户输入的密码进行加密
        String result = getPBKDF2Cryptograph(password, salt, iterCount);
        // 把加密后的密文和原密文进行比较，相同则验证成功，否则失败
        return result.equals(encryptedPassword);
    }

    /**
     * PBKDF2加密生成密文密码
     * @param password 明文密码
     * @param salt 随机盐值
     * @param iterCount 迭代次数
     * @return 密文密码
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static String getPBKDF2Cryptograph(String password, String salt, int iterCount) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        //将16进制字符串形式的salt转换成byte数组
        byte[] bytes = DatatypeConverter.parseHexBinary(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), bytes, iterCount, HASH_SIZE * 4);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();
        //将byte数组转换为16进制的字符串
        return DatatypeConverter.printHexBinary(hash);
    }


    /**
     * 生成随机盐值
     *
     */
    public static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("PBKDF2WithHmacSHA256");
        byte[] bytes = new byte[SALT_SIZE / 2];
        random.nextBytes(bytes);
        //将byte数组转换为16进制的字符串
        String salt = DatatypeConverter.printHexBinary(bytes);
        return salt;
    }

}
