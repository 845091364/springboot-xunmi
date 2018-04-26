package com.springboot.utils;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
/**
 * 
 * 图片工具类
 *
 */
public class ImageUtil {
	private static final Log logger = LogFactory.getLog(ImageUtil.class);

	// 图片格式
	public enum IMAGE_FORMAT {
		JPG("jpg"), JPEG("jpeg"), PNG("png"), GIF("gif");
		private String value;

		IMAGE_FORMAT(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	// 头像格式 16,24,32,42,48,64,120,640
	public enum IMAGE_SIZE {
		S640(640), S120(120), S64(64), S48(48), S42(42), S32(32), S24(24), S16(
				16);
		private Integer value;

		IMAGE_SIZE(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/**
	 * @param imgByte
	 *            原图字节序列
	 * @param toWidth
	 *            目标图宽度
	 * @param toHeight
	 *            目标图高度
	 * @param imgFormat
	 *            目标图片格式
	 * @return 目标图片字节序列
	 */
	public static byte[] resizeImg(byte[] imgByte, int toWidth, int toHeight,
			String imgFormat) {
		ByteArrayInputStream bin = new ByteArrayInputStream(imgByte);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			BufferedImage image = ImageIO.read(bin);
			if (image != null) {
				Image scaledImage = image.getScaledInstance(toWidth, toHeight,
						Image.SCALE_SMOOTH);
				double ratio = 0.0; // 缩放比例
				// 计算比例
				if ((image.getHeight() > toHeight)
						|| (image.getWidth() > toWidth)) {
					if (image.getHeight() > image.getWidth()) {
						ratio = (new Integer(toHeight)).doubleValue()
								/ image.getHeight();
					} else {
						ratio = (new Integer(toWidth)).doubleValue()
								/ image.getWidth();
					}
					AffineTransformOp op = new AffineTransformOp(
							AffineTransform.getScaleInstance(ratio, ratio),
							null);
					scaledImage = op.filter(image, null);
				}
				ImageIO.write(bufferImage(scaledImage), imgFormat, out);
				return out.toByteArray();
			}
			return null;
		} catch (IOException e) {
			//logger.error("zoomImage exception!", e);
			// "格式转换出错
			return null;
		}
	}

	public static BufferedImage bufferImage(Image image) {
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bufferedImage.createGraphics();
		g.drawImage(image, null, null);
		g.dispose();
		return bufferedImage;
	}

	/**
	 * 获取图片格式
	 * 
	 * @param file
	 *            图片文件
	 * @return 图片格式
	 */
	public static String getImageFormatName(File file) throws IOException {
		String formatName = null;
		ImageInputStream iis = ImageIO.createImageInputStream(file);
		Iterator<ImageReader> imageReader = ImageIO.getImageReaders(iis);
		if (imageReader.hasNext()) {
			ImageReader reader = imageReader.next();
			formatName = reader.getFormatName();
		}
		return formatName;
	}

	/**
	 * 根据byte数组，生成文件
	 */
	public static String genFile(byte[] bfile, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;

		try {
			File filePath_ = new File(filePath);
			if (!filePath_.exists() && !filePath_.isDirectory()) {// 判断文件目录是否存在
				filePath_.mkdirs();
			}

			String filePathUrl = filePath + fileName;
			File file = new File(filePathUrl);
			if (!file.exists()) {// 判断文件是否存在
				file.createNewFile();
			}
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
		//	e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					//e1.printStackTrace();
				}
			}
		}
		return fileName;
	}

	/**
	 * 图片转byte[]
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] image2Byte(File file) {
		byte[] data = null;
		FileImageInputStream input = null;
		try {
			input = new FileImageInputStream(file);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			output.close();
			input.close();
		} catch (FileNotFoundException ex1) {
			//ex1.printStackTrace();
		} catch (IOException ex1) {
			//ex1.printStackTrace();
		}
		return data;
	}

	/**
	 * 图片上传
	 * 
	 * @param fileByteData
	 *            - utf-8
	 * @return
	 */
	public static HashMap<String, Object> uploadOneFile(JSONObject param) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			if (Util.getobj(param.get("imageData"))) {
				Timestamp now = Util.getTimestampNow();
				String filePath = WebAppConfig.getInstance().getByKey(
						"file_path")
						+ File.separator
						+ Util.Date2String(now)
						+ File.separator;
				String fileName = now.getTime() + "_" + Util.getRandNum(8)
						+ "." + IMAGE_FORMAT.JPG.value;
				ImageUtil.genFile(
						AES256Encrypt.str2Bytes(param.getString("imageData")),
						filePath, fileName);
				result.put("url", "/webImages/" + fileName);
				result.put("code", 200);
				result.put("msg", "上传成功");
			} else {
				result.put("code", -200);
				result.put("msg", "上传失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", -200);
			result.put("msg", "上传失败");
		}
		return result;
	}

	/**
	 * 多文件上传
	 * 
	 * @param request
	 * @return
	 */
	public static HashMap<String, Object> uploadMoreFile(
			HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			String path = "";
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					MultipartFile file = multiRequest.getFile((String) iter
							.next());
					Timestamp now = Util.getTimestampNow();
					String filePath = WebAppConfig.getInstance().getByKey(
							"file_path")
							+ File.separator
							+ Util.Date2String(now)
							+ File.separator;
					String fileName = now.getTime() + "_" + Util.getRandNum(8)
							+ "." + ImageUtil.IMAGE_FORMAT.JPG.getValue();
					genFile(file.getBytes(), filePath, fileName);
					path = path + "/webImages?img=" + fileName + ",";
				}

				if (!path.equals("")) {
					path = path.substring(0, path.length() - 1);
				}
				result.put("url", path);
				result.put("code", Integer.valueOf(200));
				result.put("msg", "上传成功");
			} else {
				result.put("code", Integer.valueOf(-200));
				result.put("msg", "上传失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", Integer.valueOf(-200));
			result.put("msg", "上传失败");
		}
		return result;
	}

	/**
	 * 获取缩略图 byte[]
	 * 
	 * @param fileName
	 * @param w
	 * @param h
	 * @return
	 */
	public static byte[] getFileByImg(String fileName, int w, int h) {
		byte[] b = null;
		try {
			if (StringUtils.isNotEmpty(fileName)) {
				long ms = Long.parseLong(fileName.split("_")[0]);
				String fileDir = Util.Date2String(new Date(ms));
				String filePath = WebAppConfig.getInstance().getByKey(
						"file_path")
						+ File.separator + fileDir + File.separator + fileName;
				File file = new File(filePath);
				if ((w > 0) && (h > 0))
					b = resizeImg(image2Byte(file), w, h,
							getImageFormatName(file));
				else
					b = image2Byte(file);
			}
		} catch (Exception e) {
			//logger.error(e.getMessage());
		}
		return b;
	}

	public static void main(String[] args) {
		File f = new File("d:\\1.jpg");
		String fileUrl = "";
		try {
			fileUrl = ImageUtil.genFile(ImageUtil.resizeImg(
					ImageUtil.image2Byte(f), 200, 200,
					IMAGE_FORMAT.JPG.getValue()), WebAppConfig.getInstance()
					.getByKey("file_path"), Util.getTimestampNow().getTime()
					+ "." + IMAGE_FORMAT.JPG.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(fileUrl);
	}
}
