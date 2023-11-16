package com.jeebase.system.common.util;

import com.google.api.client.util.StringUtils;

import java.io.*;
import java.math.BigInteger;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {

    public static final String GZIP_ENCODE_UTF_8 = "UTF-8";
    public static final String GZIP_ENCODE_ISO_8859_1 = "ISO-8859-1";

    /** * 字符串压缩为GZIP字节数组 * * @param str * @return */
    public static byte[] compress(String str) {
        return compress(str, GZIP_ENCODE_UTF_8);
    }

    /** * 字符串压缩为GZIP字节数组 * * @param str * @param encoding * @return */
    public static byte[] compress(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return out.toByteArray();
    }

    /** * GZIP解压缩 * * @param bytes * @return */
    public static byte[] uncompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return out.toByteArray();
    }

    /** * * @param bytes * @return */
    public static String uncompressToString(byte[] bytes) {
        return uncompressToString(bytes, GZIP_ENCODE_UTF_8);
    }

    /** * * @param bytes * @param encoding * @return */
    public static String uncompressToString(byte[] bytes, String encoding) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString(encoding);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * @Desc :  gzip压缩
     * @Author : ZRP
     * @Params: [data]
     * @Return: byte[]
     * @Date : 2017/11/9 9:43
     */
    public static byte[] gzip(byte[] data) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data);
        gzip.finish();
        gzip.close();
        byte[] ret = bos.toByteArray();
        bos.close();
        return ret;
    }


    /**
     * @Desc :  gzip解压缩
     * @Author : ZRP
     * @Params: [data]
     * @Return: byte[]
     * @Date : 2017/11/9 9:47
     */
    public static byte[] ungzip(byte[] data) throws Exception{
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        GZIPInputStream gzip = new GZIPInputStream(bis);
        byte[] buf = new byte[1024];
        int num = -1;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((num = gzip.read(buf, 0, buf.length)) != -1){
            bos.write(buf, 0, num);
        }
        gzip.close();
        bis.close();
        byte[] ret = bos.toByteArray();
        bos.flush();
        bos.close();
        return ret;
    }


    //    public static void main(String[] args) {
//        //String str = "%5B%7B%22lastUpdateTime%22%3A%222011-10-28+9%3A39%3A41%22%2C%22smsList%22%3A%5B%7B%22liveState%22%3A%221";
//        String str = "{\"name\":\"张三\",\"sex\":\"1\",\"address\":\"山西原平\"}";
//        System.out.println("原长度：" + str.length());
//        System.out.println("压缩后字符串：" + GzipUtil.compress(str).toString());
//        System.out.println("解压缩后字符串：" + StringUtils.newStringUtf8(GzipUtil.uncompress(GzipUtil.compress(str))));
//        System.out.println("解压缩后字符串：" + GzipUtil.uncompressToString(GzipUtil.compress(str)));
//
//        String encodeStr = Base64.getEncoder().encodeToString(GzipUtil.compress(str));
//        System.out.println("压缩文件转码base64"+encodeStr);
//        try {
//            byte[] ret = new sun.misc.BASE64Decoder().decodeBuffer(encodeStr);
//            System.out.println("转码后"+ret);
//            System.out.println("转码后解压缩：" + GzipUtil.uncompressToString(ret));
//
//
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
    public static String binary(byte[] bytes,int i){
        return new BigInteger(1,bytes).toString(i);
    }
public static void main(String[] args) throws Exception {
//    String readPath = "/Users/fanyouyi/Downloads/nangong.png";
//    File file = new File(readPath);
//    FileInputStream in = new FileInputStream(file);
//    byte[] data = new byte[in.available()];
//    in.read(data);
//    in.close();
//    System.out.println("文件原始大小:" + data.length);
//
//    //测试压缩
//    byte[] ret1 = GzipUtil.gzip(data);
//    System.out.println("压缩之后大小:" + ret1.length);
//    byte[] ret2 = GzipUtil.ungzip(ret1);
//    System.out.println("还原之后大小:" + ret2.length);
//
//
//    //写出文件
//    String writePath = "/Users/fanyouyi/Downloads/nangong.png.zip";
//    FileOutputStream fos = new FileOutputStream(writePath);
//    fos.write(ret1);
//    fos.close();
//    String str = "你好呀";
//    System.out.println("2"+GzipUtil.binary(str.getBytes(),2));
//    System.out.println("6"+GzipUtil.binary(str.getBytes(),6));
//    System.out.println("8"+GzipUtil.binary(str.getBytes(),8));
//    System.out.println("10"+GzipUtil.binary(str.getBytes(),10));
//    System.out.println("16"+GzipUtil.binary(str.getBytes(),16));
//    System.out.println("32"+GzipUtil.binary(str.getBytes(),32));
//    System.out.println("64"+GzipUtil.binary(str.getBytes(),64));
    System.out.println(1652341037443L/1000+"s");
    System.out.println(1652341136698l/1000+"s");
}
}
