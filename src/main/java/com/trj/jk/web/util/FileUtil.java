package com.trj.jk.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

/**
 * 文件工具类
 */
public class FileUtil {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;

    /**
     * 根据URl下载文件
     *
     * @param fileUrl：文件资源url
     * @param downPath：下载的文件存储目录文件夹
     */
    public static String getFileByUrl(String fileUrl, String downPath) {
        LOG.info("file url:" + fileUrl);
        LOG.info("file dir:" + downPath);
        File savePath = new File(downPath);
        if (!savePath.exists()) {
            savePath.mkdir();
        }
        String[] urlname = fileUrl.split("/");
        int len = urlname.length - 1;
        String uname = urlname[len];//获取文件名
        String filePath = savePath + "/" + uname;
        LOG.info("file path:" + filePath);
        OutputStream oputstream = null;
        InputStream iputstream = null;
        try {
            File file = new File(filePath);//创建新文件
            if (file != null && !file.exists()) {
                file.createNewFile();
            }
            oputstream = new FileOutputStream(file);
            URL url = new URL(fileUrl);
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            uc.setDoInput(true);//设置是否要从 URL 连接读取数据,默认为true
            uc.setConnectTimeout(DEF_CONN_TIMEOUT);
            uc.setReadTimeout(DEF_READ_TIMEOUT);
            uc.connect();
            iputstream = uc.getInputStream();
            LOG.info("file size is:" + uc.getContentLength());//打印文件长度
            byte[] buffer = new byte[4 * 1024];
            int byteRead = -1;
            while ((byteRead = (iputstream.read(buffer))) != -1) {
                oputstream.write(buffer, 0, byteRead);
            }
            oputstream.flush();
            iputstream.close();
            oputstream.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("通过URl读取文件失败！");
            LOG.error(e.getMessage());
            throw new RuntimeException("通过URl读取文件失败！");
        }
        LOG.info("生成文件路径：filePath={}", filePath);
        return filePath;
    }

    public static File saveFile(MultipartFile file, String uploadPath) {
        File uploadFile = null;
        try {
            File upload = new File(uploadPath);
            if (!upload.exists()) {
                upload.mkdirs();
            }
            String path = uploadPath + File.separator + UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename();
            uploadFile = new File(path);
            file.transferTo(uploadFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            LOG.error("MultipartFile转file失败" + e.getMessage());
            throw new RuntimeException("MultipartFile转file失败" + e.getMessage());
        } catch (IOException e) {
            LOG.error("MultipartFile转file失败" + e.getMessage());
            throw new RuntimeException("MultipartFile转file失败" + e.getMessage());
        }
        return uploadFile;
    }

    /**
     * 将网络图片进行Base64位编码
     *
     * @param imageUrl 图片的url路径，如http://.....xx.jpg
     * @return
     */
    public static String encodeImgageToBase64(URL imageUrl) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageUrl);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
    }

    /**
     * 将本地图片进行Base64位编码
     *
     * @param imageFile 图片的url路径，如http://.....xx.jpg
     * @return
     */
    public static String encodeImgageToBase64(File imageFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            String fileName = imageFile.getName();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String formatName = (null == suffix ? "jpg" : suffix);
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, formatName, outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
    }

    /**
     * 将Base64位编码的图片进行解码，并保存到指定目录
     *
     * @param base64 base64编码的图片信息
     * @return
     */
    public static File decodeBase64ToImage(String base64, String uploadPath) {
        BASE64Decoder decoder = new BASE64Decoder();
        File uploadFile = null;
        try {
            uploadFile = new File(uploadPath+"/"+UUID.randomUUID().toString()+".jpg");
            FileOutputStream write = new FileOutputStream(uploadFile);
            byte[] decoderBytes = decoder.decodeBuffer(base64);
            write.write(decoderBytes);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadFile;
    }

}
