package com.cyou.fz.common.base.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * 加密工具类.
 * <p/>
 * 类说明: Base64编码的加码与解码.
 * <p/>
 * 类用途：将各种编码格式的数据转成Base64格式的数据
 * 将Base64格式的数据转成原先编码格式的数据
 */
public final class EncryptUtil {

    /**
     * 默认字符编码
     */
    protected static final String DEFAULT_CHAR_ENCODING = "UTF-8";
    /**
     * 基本长度
     */
    private static final int BASELENGTH = 255;
    /**
     * LOOKUPLENGTH
     */
    private static final int LOOKUPLENGTH = 64;
    /**
     * 24bit
     */
    private static final int TWENTYFOURBITGROUP = 24;
    /**
     * 8bit
     */
    private static final int EIGHTBIT = 8;
    /**
     * 16bit
     */
    private static final int SIXTEENBIT = 16;
    /**
     * 6bit
     */
    @SuppressWarnings("unused")
    private static final int SIXBIT = 6;
    /**
     * 4byte
     */
    private static final int FOURBYTE = 4;
    /**
     * SIGN
     */
    private static final int SIGN = -128;
    /**
     * (byte)'='
     */
    private static final byte PAD = (byte) '=';
    /**
     * 空的byte数组
     */
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private final static String HEX_NUMS_STR = "0123456789ABCDEF";
    private final static Integer SALT_LENGTH = 12;
    private static byte[] base64Alphabet = new byte[BASELENGTH];
    private static byte[] lookUpBase64Alphabet = new byte[LOOKUPLENGTH];

    static {

        for (int i = 0; i < BASELENGTH; i++) {
            base64Alphabet[i] = -1;
        }
        for (int i = 'Z'; i >= 'A'; i--) {
            base64Alphabet[i] = (byte) (i - 'A');
        }
        for (int i = 'z'; i >= 'a'; i--) {
            base64Alphabet[i] = (byte) (i - 'a' + 26);
        }

        for (int i = '9'; i >= '0'; i--) {
            base64Alphabet[i] = (byte) (i - '0' + 52);
        }

        base64Alphabet['+'] = 62;
        base64Alphabet['/'] = 63;

        for (int i = 0; i <= 25; i++) {
            lookUpBase64Alphabet[i] = (byte) ('A' + i);
        }

        for (int i = 26, j = 0; i <= 51; i++, j++) {
            lookUpBase64Alphabet[i] = (byte) ('a' + j);
        }

        for (int i = 52, j = 0; i <= 61; i++, j++) {
            lookUpBase64Alphabet[i] = (byte) ('0' + j);
        }

        lookUpBase64Alphabet[62] = (byte) '+';
        lookUpBase64Alphabet[63] = (byte) '/';

    }

    /**
     * 判断指定字符串是否是Base64编码格式
     *
     * @param isValidString 指定字符串
     * @return 如果是就返回true， 否则返回false
     *         <p/>
     *         <pre>
     *         修改日期      修改人    修改原因
     *         2010-04-13       整理
     *         </pre>
     */
    public static boolean isBase64(String isValidString) {
        return (isBase64(isValidString.getBytes()));
    }

    /**
     * 判断指定byte类型是否是Base64编码
     *
     * @param octect byte类型
     * @return 如果是则返回true, 否则返回false
     *         <p/>
     *         <pre>
     *         修改日期      修改人    修改原因
     *         2010-04-13           整理
     *         </pre>
     */
    public static boolean isBase64(byte octect) {
        // Should we ignore white space?
        return (octect == PAD || base64Alphabet[octect] != -1);
    }

    /**
     * 判断指定指定byte数组内容是否都为Base64编码
     *
     * @param arrayOctect byte数组
     * @return 如果是则返回true, 否则返回false
     *         <p/>
     *         <pre>
     *         修改日期      修改人    修改原因
     *         2010-04-13             整理
     *         </pre>
     */
    public static boolean isBase64(byte[] arrayOctect) {
        int length = arrayOctect.length;
        if (length == 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (!EncryptUtil.isBase64(arrayOctect[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将二进制数组转成 Base64 编码
     *
     * @param binaryData 待转二进制数组
     * @return Base64编码的数组
     *         <p/>
     *         <pre>
     *         修改日期      修改人    修改原因
     *         2010-04-13       整理
     *         </pre>
     */
    public static byte[] encodeBase64(byte[] binaryData) {
        if (binaryData == null) {
            binaryData = EMPTY_BYTE_ARRAY;
        }

        int lengthDataBits = binaryData.length * EIGHTBIT;
        int fewerThan24bits = lengthDataBits % TWENTYFOURBITGROUP;
        int numberTriplets = lengthDataBits / TWENTYFOURBITGROUP;
        byte encodedData[] = null;

        if (fewerThan24bits != 0) {
            //data not divisible by 24 bit
            encodedData = new byte[(numberTriplets + 1) * 4];
        } else {
            // 16 or 8 bit
            encodedData = new byte[numberTriplets * 4];
        }

        byte k = 0, l = 0, b1 = 0, b2 = 0, b3 = 0;

        int encodedIndex = 0;
        int dataIndex = 0;
        int i = 0;
        for (i = 0; i < numberTriplets; i++) {

            dataIndex = i * 3;
            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex + 1];
            b3 = binaryData[dataIndex + 2];

            l = (byte) (b2 & 0x0f);
            k = (byte) (b1 & 0x03);

            encodedIndex = i * 4;
            byte val1 = ((b1 & SIGN) == 0)
                    ? (byte) (b1 >> 2)
                    : (byte) ((b1) >> 2 ^ 0xc0);

            byte val2 = ((b2 & SIGN) == 0)
                    ? (byte) (b2 >> 4)
                    : (byte) ((b2) >> 4 ^ 0xf0);

            byte val3 = ((b3 & SIGN) == 0)
                    ? (byte) (b3 >> 6)
                    : (byte) ((b3) >> 6 ^ 0xfc);

            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | (k << 4)];
            encodedData[encodedIndex + 2] = lookUpBase64Alphabet[(l << 2) | val3];
            encodedData[encodedIndex + 3] = lookUpBase64Alphabet[b3 & 0x3f];
        }

        // form integral number of 6-bit groups
        dataIndex = i * 3;
        encodedIndex = i * 4;
        if (fewerThan24bits == EIGHTBIT) {
            b1 = binaryData[dataIndex];
            k = (byte) (b1 & 0x03);
            byte val1 = ((b1 & SIGN) == 0)
                    ? (byte) (b1 >> 2)
                    : (byte) ((b1) >> 2 ^ 0xc0);

            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[k << 4];
            encodedData[encodedIndex + 2] = PAD;
            encodedData[encodedIndex + 3] = PAD;
        } else if (fewerThan24bits == SIXTEENBIT) {
            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex + 1];
            l = (byte) (b2 & 0x0f);
            k = (byte) (b1 & 0x03);

            byte val1 = ((b1 & SIGN) == 0)
                    ? (byte) (b1 >> 2)
                    : (byte) ((b1) >> 2 ^ 0xc0);

            byte val2 = ((b2 & SIGN) == 0)
                    ? (byte) (b2 >> 4)
                    : (byte) ((b2) >> 4 ^ 0xf0);

            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | (k << 4)];
            encodedData[encodedIndex + 2] = lookUpBase64Alphabet[l << 2];
            encodedData[encodedIndex + 3] = PAD;
        }
        return encodedData;
    }

    /**
     * 将ISO-8859-1的数据转成Base64格式后返回
     *
     * @param data ISO--8859-1的数据
     * @return Base64格式的数据
     *         <p/>
     *         <pre>
     *         修改日期      修改人    修改原因
     *         2010-04-13            整理
     *         </pre>
     */
    public static String encodeBase64(String data) {
        try {
            return encodeBase64(data, DEFAULT_CHAR_ENCODING);
        } catch (UnsupportedEncodingException uee) {
            throw new IllegalStateException(uee.toString());
        }
    }

    /**
     * 将指定编码的数据转成Base64格式后返回
     *
     * @param data         数据
     * @param charEncoding 编码
     * @return Base64编码字符串
     *         <p/>
     *         <pre>
     *         修改日期      修改人    修改原因
     *         2010-04-13            整理
     *         </pre>
     */
    public static String encodeBase64(String data, String charEncoding)
            throws UnsupportedEncodingException {

        // Check arguments
        if (data == null) {
            data = "";
        }
        if (charEncoding == null) {
            charEncoding = DEFAULT_CHAR_ENCODING;
        }

        // Convert to byte[]
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(bos, charEncoding);
        try {
            osw.write(data);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.toString());
        }

        // Encode
        byte[] encodedData = encodeBase64(bos.toByteArray());

        // Convert to String
        if (encodedData == null) {
            return "";
        }
        bos = new ByteArrayOutputStream(encodedData.length);
        try {
            bos.write(encodedData);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.toString());
        }

        return bos.toString(charEncoding);
    }

    /**
     * 将Base64编码的字节数组转成原始二进制数组
     *
     * @param base64Data 格式数组
     * @return 原始二进制数组
     *         <p/>
     *         <pre>
     *         修改日期      修改人    修改原因
     *         2010-04-13         整理
     *         </pre>
     */
    public static byte[] decodeBase64(byte[] base64Data) {
        // Should we throw away anything not in base64Data ?

        // handle the edge case, so we don't have to worry about it later
        if (base64Data.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }

        int numberQuadruple = base64Data.length / FOURBYTE;
        byte decodedData[] = null;
        byte b1 = 0, b2 = 0, b3 = 0, b4 = 0, marker0 = 0, marker1 = 0;

        int encodedIndex = 0;
        int dataIndex = 0;
        {
            // this block sizes the output array properly - rlw
            int lastData = base64Data.length;
            // ignore the '=' padding
            while (base64Data[lastData - 1] == PAD) {
                if (--lastData == 0) {
                    return EMPTY_BYTE_ARRAY;
                }
            }
            decodedData = new byte[lastData - numberQuadruple];
        }

        for (int i = 0; i < numberQuadruple; i++) {
            dataIndex = i * 4;
            marker0 = base64Data[dataIndex + 2];
            marker1 = base64Data[dataIndex + 3];

            b1 = base64Alphabet[base64Data[dataIndex]];
            b2 = base64Alphabet[base64Data[dataIndex + 1]];

            if (marker0 != PAD && marker1 != PAD) {
                //No PAD e.g 3cQl
                b3 = base64Alphabet[marker0];
                b4 = base64Alphabet[marker1];

                decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
                decodedData[encodedIndex + 1] = (byte) (((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf));
                decodedData[encodedIndex + 2] = (byte) (b3 << 6 | b4);
            } else if (marker0 == PAD) {
                //Two PAD e.g. 3c[Pad][Pad]
                decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
            } else if (marker1 == PAD) {
                //One PAD e.g. 3cQ[Pad]
                b3 = base64Alphabet[marker0];

                decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
                decodedData[encodedIndex + 1] = (byte) (((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf));
            }
            encodedIndex += 3;
        }
        return decodedData;
    }

    /**
     * 将16进制字符串转换成数组
     *
     * @return byte[]
     * @author jacob
     */
    private static byte[] hexStringToByte(String hex) {
        /* len为什么是hex.length() / 2 ?
         * 首先，hex是一个字符串，里面的内容是像16进制那样的char数组
         * 用2个16进制数字可以表示1个byte，所以要求得这些char[]可以转化成什么样的byte[]，首先可以确定的就是长度为这个char[]的一半
         */
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4 | HEX_NUMS_STR
                    .indexOf(hexChars[pos + 1]));
        }
        return result;
    }

    /**
     * 将数组转换成16进制字符串
     *
     * @return String
     * @author jacob
     */
    private static String byteToHexString(byte[] salt) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < salt.length; i++) {
            String hex = Integer.toHexString(salt[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    /**
     * 密码验证
     *
     * @param desc 目标串
     * @param src  匹配串
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public static boolean validHex(String desc, String src)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] pwIndb = hexStringToByte(src);
        //定义salt
        byte[] salt = new byte[SALT_LENGTH];
        System.arraycopy(pwIndb, 0, salt, 0, SALT_LENGTH);
        //创建消息摘要对象
        MessageDigest md = MessageDigest.getInstance("MD5");
        //将盐数据传入消息摘要对象
        md.update(salt);
        md.update(desc.getBytes("UTF-8"));
        byte[] digest = md.digest();
        //声明一个对象接收数据库中的口令消息摘要
        byte[] digestIndb = new byte[pwIndb.length - SALT_LENGTH];
        //获得数据库中口令的摘要
        System.arraycopy(pwIndb, SALT_LENGTH, digestIndb, 0, digestIndb.length);
        //比较根据输入口令生成的消息摘要和数据库中的口令摘要是否相同
        if (Arrays.equals(digest, digestIndb)) {
            //口令匹配相同
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获得md5之后的16进制字符
     *
     * @param source 待加密串
     * @return String md5加密后密码字符
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public static String encodeHex(String source) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //拿到一个随机数组，作为盐
        byte[] pwd = null;
        SecureRandom sc = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        sc.nextBytes(salt);

        //声明摘要对象，并生成
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(salt);
        md.update(source.getBytes("UTF-8"));
        byte[] digest = md.digest();

        pwd = new byte[salt.length + digest.length];
        System.arraycopy(salt, 0, pwd, 0, SALT_LENGTH);
        System.arraycopy(digest, 0, pwd, SALT_LENGTH, digest.length);
        return byteToHexString(pwd);
    }
}

