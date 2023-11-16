package com.jeebase.system.common.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Make2DBarCodeUtil {
	static BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	public static String getBarcode(String str, Integer width, Integer height) {
	      ByteArrayOutputStream out = new ByteArrayOutputStream();
	      BitMatrix bitMatrix = null;
	      if(width == null || width.intValue() < 200) {
	         width = Integer.valueOf(400);
	      }

	      if(height == null || height.intValue() < 50) {
	         height = Integer.valueOf(150);
	      }

	      try {
	         Hashtable e = new Hashtable();
	         e.put(EncodeHintType.CHARACTER_SET, "utf-8");
	         bitMatrix = (new MultiFormatWriter()).encode(str, BarcodeFormat.CODE_128, width.intValue(), height.intValue(), e);
	         BufferedImage image = toBufferedImage(bitMatrix);
	         ImageIO.write(image, "png", out);
	         byte[] bytes = out.toByteArray();
	         BASE64Encoder encoder = new BASE64Encoder();
	         str = encoder.encodeBuffer(bytes).trim();
	      } catch (IOException var9) {
	         var9.printStackTrace();
	      } catch (Exception var10) {
	         var10.printStackTrace();
	      }

	      return str;
	   }

	public static String inputStream2Base64(InputStream is) throws Exception {
		byte[] data = null;
		try {
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
			byte[] buff = new byte[100];
			int rc = 0;
			while ((rc = is.read(buff, 0, 100)) > 0) {
				swapStream.write(buff, 0, rc);
			}
			data = swapStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new Exception("输入流关闭异常");
				}
			}
		}

		return Base64.getEncoder().encodeToString(data);
	}



	private static BufferedImage toBufferedImage(BitMatrix matrix) {
	      int width = matrix.getWidth();
	      int height = matrix.getHeight();
	      BufferedImage image = new BufferedImage(width, height, 1);

	      for(int x = 0; x < width; ++x) {
	         for(int y = 0; y < height; ++y) {
	            image.setRGB(x, y, matrix.get(x, y)?-16777216:-1);
	         }
	      }

	      return image;
	   }
	public static String base64pdf2Image(String base64Pdf) {

		String jpg_base64 = null;
		try {
			// Base64解码
			byte[] pdf_bytes = decoder.decodeBuffer(base64Pdf);
			PDDocument doc = PDDocument.load(pdf_bytes);
			int size = doc.getNumberOfPages();
			/*图像合并使用的参数*/
			//定义宽度
			int width = 0;
			// 保存一张图片中的RGB数据
			int[] singleImgRGB;
			// 定义高度，后面用于叠加
			int shiftHeight = 0;
			//保存每张图片的像素值
			BufferedImage imageResult = null;
			// 利用PdfBox生成图像
			PDDocument pdDocument = doc;
			PDFRenderer renderer = new PDFRenderer(pdDocument);
			/*根据总页数, 按照50页生成一张长图片的逻辑, 进行拆分*/
			// 每50页转成1张图片
			int pageLength = size; //有多少转多少
			// 总计循环的次数
			int totalCount = pdDocument.getNumberOfPages() / pageLength + 1;
			for (int m = 0; m < totalCount; m++) {
				for (int i = 0; i < pageLength; i++) {
					int pageIndex = i + (m * pageLength);
					if (pageIndex == pdDocument.getNumberOfPages()) {
						System.out.println("循环次数 m = " + m);
						break;
					}
					// dpi为图片的dpi，dpi越大，则图片越清晰，图片越大，转换耗费的时间也越多
					BufferedImage image = renderer.renderImageWithDPI(pageIndex, 86, ImageType.RGB);
					int imageHeight = image.getHeight();
					int imageWidth = image.getWidth();
					if (i == 0) {
						//计算高度和偏移量
						//使用第一张图片宽度;
						width = imageWidth;
						// 保存每页图片的像素值
						// 加个判断：如果m次循环后所剩的图片总数小于pageLength，则图片高度按剩余的张数绘制，否则会出现长图片下面全是黑色的情况
						if ((pdDocument.getNumberOfPages() - m * pageLength) < pageLength) {
							imageResult = new BufferedImage(width, imageHeight * (pdDocument.getNumberOfPages() - m * pageLength), BufferedImage.TYPE_INT_RGB);
						} else {
							imageResult = new BufferedImage(width, imageHeight * pageLength, BufferedImage.TYPE_INT_RGB);
						}
					} else {
						// 将高度不断累加
						shiftHeight += imageHeight;
					}
					singleImgRGB = image.getRGB(0, 0, width, imageHeight, null, 0, width);
					imageResult.setRGB(0, shiftHeight, width, imageHeight, singleImgRGB, 0, width);
				}
				// 这个很重要，下面会有说明
				shiftHeight = 0;
			}
			pdDocument.close();
			ByteArrayOutputStream baos;//io流
			baos = new ByteArrayOutputStream();
			ImageIO.write(imageResult, "jpg", baos);//写入流中
			byte[] jpg_Bytes = baos.toByteArray();//转换成字节
			BASE64Encoder encoder = new BASE64Encoder();
			jpg_base64 = encoder.encodeBuffer(jpg_Bytes).trim();//转换成base64串
			jpg_base64 = jpg_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
			baos.close();
			doc.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return jpg_base64;

	}

}
