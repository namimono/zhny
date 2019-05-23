package org.rcisoft.base.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

public class QRCodeUtils {

	/**
    * 定义二维码的参数
    */
   HashMap hints=new HashMap();

   /** 数据字符编码格式 **/
   private static String codeFormate="UTF-8";
   /** 二维码纠错等级  **/
   private static ErrorCorrectionLevel QRCodeErrorLevel=ErrorCorrectionLevel.M;
   /** 图片边距 **/
   private static int imagePadding=0;
   /** 二维码尺寸 **/
   private static int QRCODE_SIZE = 400;
   /** 输出图片格式  **/
   private static String FORMAT_NAME = "JPG";
   /** 是否去除外部的边框 **/
   private static boolean cleanPadding=false;
	
	private static BufferedImage createQRCodeImage(String dataInfo,Hashtable<EncodeHintType, Object>hints) throws WriterException{
		if(hints==null || hints.isEmpty()){
			hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.ERROR_CORRECTION, QRCodeErrorLevel);  
	        hints.put(EncodeHintType.CHARACTER_SET, codeFormate);  
	        hints.put(EncodeHintType.MARGIN, imagePadding);
		}
        BitMatrix bitMatrix = new MultiFormatWriter().encode(dataInfo, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);  
        //此处判断是否需要将图片两边的留白进行处理
        BufferedImage resultImage=null;
        if(cleanPadding){
        	//1.1去白边
        	int[] rec = bitMatrix.getEnclosingRectangle();  
            int resWidth = rec[2] + 5;  
            int resHeight = rec[3] + 5;  
            BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);  
            resMatrix.clear();  
            for (int i = 0; i < resWidth; i++) {  
                for (int j = 0; j < resHeight; j++) {  
                    if (bitMatrix.get(i + rec[0], j + rec[1])) { 
                         resMatrix.set(i, j); 
                    } 
                }  
            }  
            //2
            int width = resMatrix.getWidth();
            int height = resMatrix.getHeight();
            resultImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                	resultImage.setRGB(x, y, resMatrix.get(x, y) == true ? Color.BLACK.getRGB():Color.WHITE.getRGB());
                }

            }
        }else{
        	int width = bitMatrix.getWidth();  
            int height = bitMatrix.getHeight();
            resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
            for (int x = 0; x < width; x++) {  
                for (int y = 0; y < height; y++) {  
                	resultImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);  
                }  
            }
        }
        //此处开始将图片输出出来
        return resultImage;
	}


	/**
	 * @param dataInfo 二维码生成参数
	 * @param filePath 二维码存放路径
	 * @param fileName 二维码保存名称
	 * @return
	 * @throws WriterException
	 * @throws IOException
	 */
	public static String createQRCodeFile(String dataInfo,String filePath,String fileName) throws WriterException, IOException{
		//输出二维码
		BufferedImage QRCodeImage = createQRCodeImage(dataInfo,null);
		//判断文件路径是否存在
		File saveDir=new File(filePath);
		if(!saveDir.exists()){
			saveDir.mkdirs();
		}
		String filepathStr=filePath+"/"+fileName+"."+FORMAT_NAME;
		//开始写文件啦
		ImageIO.write(QRCodeImage, FORMAT_NAME, new File(filepathStr));
		return filepathStr;
	}

	
}
