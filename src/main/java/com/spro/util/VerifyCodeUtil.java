package com.spro.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @description: Java生成图片二维码工具类
 * @package_name: com.sean.utils
 * @data: 2020-6-30 14:16
 * @author: Sean
 * @version: V1.0
 */
public class VerifyCodeUtil {
    //	提供图片的宽、高数据
    private int w =70;
    private int h = 35;
    //	创建随机对象
    private Random r = new Random();
    //	手动确定使用字体、生成数据、的范围
    private String[] forName = {"宋体","华文楷体","黑体","微雅软黑","楷体_GB2312"};
    private String code= "23456789abcdefghijkmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
    //	图片背景色
    private Color bgcolor = new Color(255, 255, 255);
    //	最终生成的文本字符串
    private String text;

    //	随机颜色方法
    private Color randomcolor(){
//		R,G,B取值为0-150，是为了别靠255（白色）太近，防止看不清
        int R = r.nextInt(150);
        int G = r.nextInt(150);
        int B = r.nextInt(150);
        return new Color(R, G, B);
    }

    //	随机字体方法
    private Font randomfont(){
//		随机获取forname的一个下表，来得到相应字体
        int index = r.nextInt(forName.length);
//		第二个参数为字体样式   （0 五样式，1 粗体，2 斜体，3 粗体斜体）
//		第三个参数为字体大小
        return new Font(forName[index], r.nextInt(4), r.nextInt(4)+24);
    }

    //	随机生成字符
    private char randomchar(){
        int index = r.nextInt(code.length());
//		从字符串中拿出第index个字符
        return code.charAt(index);
    }

    //	生成验证码大白板
    private BufferedImage CreateImage(){
//  创建bufferedimage对象，设置图片的 长为w，宽为h，所创建图像的类型为TYPE_INT_RGB
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
//  拿到image对象的2D画笔
        Graphics2D g = (Graphics2D)bi.getGraphics();
//  设置图像背景色
        g.setColor(this.bgcolor);
//   用画笔填充图片，从（0,0）位置开始，长为w，宽为h，使用图形上下文的当前颜色填充该矩形。
        g.fillRect(0, 0, w, h);
//  返回这张图片（纯背景图片）
        return bi;
    }

    //	生成验正码
    public BufferedImage getImage(){
//  调用 CreateImage()函数，生成image（验证码图片）背景
        BufferedImage image = CreateImage();
//  2D画笔
        Graphics2D g2 = (Graphics2D)image.getGraphics();
        StringBuilder sb = new StringBuilder();
// 循环生成4个字符，添加到字符串sb 之后作为验证码
        for(int i=0;i<4;i++){
            String s = randomchar()+"";
            sb.append(s);
            //将图片四等分，每个新生成的字符画图时起点不同
            float x = i*1.0F*w/4;
            g2.setFont(randomfont());//随机字体
            g2.setColor(randomcolor());//随机颜色
            g2.drawString(s, x, h-5);//绘制字符在image上
        }
        this.text = sb.toString();//拿到完整的验证码字符串
        return image;//返回已生成验证码的图片
    }
    public String text (){
        return text;//返回验证码值
    }
    //	保存图片方法
    public static void output(BufferedImage image,OutputStream out) throws IOException{
        ImageIO.write(image, "JPEG", out);
    }
}
