//package com.jeebase.system.common.util;
//
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.*;
//
//public class CompressPic {
//
//    /**
//     * 按原比例压缩到指定大小以内（略小）
//     * @param srcPath 源图片地址
//     * @param targetPath  压缩后地址
//     * @return
//     * @throws Exception
//     */
//    public String CompressPicture(String srcPath,String targetPath) throws Exception {
//        double cutPercent=0.1;
//        File file=new File(srcPath);
//        BufferedImage bufferedImage= ImageIO.read(new FileInputStream(file));
//        int width=bufferedImage.getWidth(null);
//        int height=bufferedImage.getHeight(null);
//        long fileLength=file.length();
//        while((fileLength/1024)>=300) {
//            width=(int)(width*(1-cutPercent));
//            height=(int)(height*(1-cutPercent));
//            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//            tag.getGraphics().drawImage(bufferedImage, 0, 0, width, height, null); // 绘制缩小后的图
//            FileOutputStream deskImage = new FileOutputStream(targetPath); // 输出到文件流
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
//            encoder.encode(tag); // 近JPEG编码
//            deskImage.close();
//
//            File file1=new File(targetPath);
//            BufferedImage bufferedImage1=ImageIO.read(new FileInputStream(file1));
//            width=bufferedImage1.getWidth(null);
//            height=bufferedImage1.getHeight(null);
//            fileLength=file1.length();
//        }
//
//        return targetPath;
//    }
//
//    /**
//     * 按比例缩小二倍
//     * @param in
//     * @return
//     */
//    public static ByteArrayInputStream reduceIn(InputStream in) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try {
//            // 开始读取文件并进行压缩
//            Image src = ImageIO.read(in);
//
//            // 构造一个类型为预定义图像类型之一的 BufferedImage
//            BufferedImage tag = new BufferedImage((int) src.getWidth(null), (int) src.getHeight(null), BufferedImage.TYPE_INT_RGB);
//
//            //绘制图像  getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
//            //Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
//            tag.getGraphics().drawImage(src.getScaledInstance((int) src.getWidth(null)/2, (int) src.getHeight(null)/2, Image.SCALE_SMOOTH), 0, 0, null);
//
//            //将图片按JPEG压缩，保存到out中
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
//            encoder.encode(tag);
//            //关闭文件输出流
//
//        } catch (Exception ef) {
//            ef.printStackTrace();
//        }
//        return new ByteArrayInputStream(baos.toByteArray());
//    }
//
//
//    /**
//     * 指定图片宽度和高度和压缩比例对图片进行压缩
//     *
//     * @param imgsrc
//     *            源图片地址
//     * @param imgdist
//     *            目标图片地址
//     * @param widthdist
//     *            压缩后图片的宽度
//     * @param heightdist
//     *            压缩后图片的高度
//     * @param rate
//     *            压缩的比例
//     */
//    public static String reduceImg(String imgsrc, String imgdist, int widthdist, int heightdist, Float rate) {
//        try {
//            File srcfile = new File(imgsrc);
//            // 检查图片文件是否存在
//            if (!srcfile.exists()) {
//                System.out.println("文件不存在");
//            }
//            // 如果比例不为空则说明是按比例压缩
//            if (rate != null && rate > 0) {
//                //获得源图片的宽高存入数组中
//                int[] results = getImgWidthHeight(srcfile);
//                if (results == null || results[0] == 0 || results[1] == 0) {
//                    return null;
//                } else {
//                    //按比例缩放或扩大图片大小，将浮点型转为整型
//                    widthdist = (int) (results[0] * rate);
//                    heightdist = (int) (results[1] * rate);
//                }
//            }
//            // 开始读取文件并进行压缩
//            Image src = ImageIO.read(srcfile);
//
//            // 构造一个类型为预定义图像类型之一的 BufferedImage
//            BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);
//
//            //绘制图像  getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
//            //Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
//            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);
//
//            //创建文件输出流
//            FileOutputStream out = new FileOutputStream(imgdist);
//            //将图片按JPEG压缩，保存到out中
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
//            //关闭文件输出流
//            out.close();
//        } catch (Exception ef) {
//            ef.printStackTrace();
//        }
//        return imgdist;
//    }
//
//    /**
//     * 获取图片宽度和高度
//     *
//     * @param //图片路径
//     * @return 返回图片的宽度
//     */
//    public static int[] getImgWidthHeight(File file) {
//        InputStream is = null;
//        BufferedImage src = null;
//        int result[] = { 0, 0 };
//        try {
//            // 获得文件输入流
//            is = new FileInputStream(file);
//            // 从流里将图片写入缓冲图片区
//            src = ImageIO.read(is);
//            result[0] =src.getWidth(null); // 得到源图片宽
//            result[1] =src.getHeight(null);// 得到源图片高
//            is.close();  //关闭输入流
//        } catch (Exception ef) {
//            ef.printStackTrace();
//        }
//
//        return result;
//    }
//
//
//
//    /**
//     * 等比例压缩算法：
//     * 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图,宽高按原比例
//     * @param srcURL 原图地址
//     * @param deskURL 缩略图地址
//     * @param comBase 压缩基数,或是宽或是高中的一个
//     * @param scale 压缩限制(宽/高)比例  一般用1：
//     * 当scale>=1,缩略图height=comBase,width按原图宽高比例;若scale<1,缩略图width=comBase,height按原图宽高比例
//     * @throws Exception
//     * @author shenbin
//     * @createTime 2014-12-16
//     * @lastModifyTime 2014-12-16
//     */
//    public static String saveMinPhoto(String srcURL, String deskURL, double comBase,
//                                      double scale) throws Exception {
//        File srcFile = new java.io.File(srcURL);
//        Image src = ImageIO.read(srcFile);
//        int srcHeight = src.getHeight(null);
//        int srcWidth = src.getWidth(null);
//        int deskHeight = 0;// 缩略图高
//        int deskWidth = 0;// 缩略图宽
//        double srcScale = (double) srcHeight / srcWidth;
//        System.out.println("高"+srcHeight+"宽"+srcWidth);
//        /**缩略图宽高算法*/
//        if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
//            if (srcScale >= scale || 1 / srcScale > scale) {
//                if (srcScale > scale) {
//                    deskHeight = (int) comBase;
//                    deskWidth = srcWidth * deskHeight / srcHeight;
//                }else if(srcScale ==scale){
//                    deskHeight = 2000;
//                    deskWidth = 2000;
//                } else {
//                    deskWidth = (int) comBase;
//                    deskHeight = srcHeight * deskWidth / srcWidth;
//                }
//            } else {
//                if ((double) srcHeight > comBase) {
//                    deskHeight = (int) comBase;
//                    deskWidth = srcWidth * deskHeight / srcHeight;
//                } else {
//                    deskWidth = (int) comBase;
//                    deskHeight = srcHeight * deskWidth / srcWidth;
//                }
//            }
//        } else {
//            deskHeight = srcHeight;
//            deskWidth = srcWidth;
//        }
//        BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
//        tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); //绘制缩小后的图
//        FileOutputStream deskImage = new FileOutputStream(deskURL); //输出到文件流
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
//        encoder.encode(tag); //近JPEG编码
//        deskImage.close();
//        return deskURL;
//    }
//
//
//
//
//
//   /* public static void main(String[] args) {
//
//        File srcfile = new File("d:/ZTestForWork/csj.jpg");
//        File distfile = new File("d:/ZTestForWork/zipedcsj.jpg");
//
//        System.out.println("压缩前图片大小：" + srcfile.length());
//        String imgdist=reduceImg("d:/ZTestForWork/test.jpg", "d:/ZTestForWork/test.jpg", 200, 240, null);
//        System.out.println("压缩后图片大小：" + distfile.length());
//        System.out.println("压缩后的路径为："+imgdist);
//    }*/
//
//    public static void main(String args[]) throws Exception {
//        File file=new File("/Users/fanyouyi/Downloads/字体/test1.jpg");
//        BufferedImage bufferedImage=ImageIO.read(new FileInputStream(file));
//        System.out.println(file.length());
//        int width=bufferedImage.getWidth(null);
//        int height=bufferedImage.getHeight(null);
//        System.out.println("压缩前图片的宽为："+width);
//        System.out.println("压缩前图片的高为："+height);
//        //CompressPic.saveMinPhoto("/Users/fanyouyi/Downloads/字体/test1.png", "/Users/fanyouyi/Downloads/字体/test1_1.png",2500, 1);
//        String imgdist=reduceImg("/Users/fanyouyi/Downloads/字体/test1.jpg", "/Users/fanyouyi/Downloads/字体/test1_j.jpg", width/2, height/2, null);
//        File file1=new File("/Users/fanyouyi/Downloads/字体/test1_j.jpg");
//        BufferedImage bufferedImage1=ImageIO.read(new FileInputStream(file1));
//        System.out.println(file1.length());
//        int width1=bufferedImage1.getWidth(null);
//        int height1=bufferedImage1.getHeight(null);
//        System.out.println("压缩后图片的宽为："+width1);
//        System.out.println("压缩后图片的高为："+height1);
//    }
//}
