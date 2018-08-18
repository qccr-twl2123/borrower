package com.trj.jk.web.service.face;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;

import com.alibaba.fastjson.JSON;
import com.trj.commons.result.Result;
import com.trj.jk.web.enums.CheckFaceImgEnum;
import com.trj.jk.web.exception.RRException;
import com.trj.jk.web.service.UserFaceLogService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.util.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.UserBasicCriteria;
import com.trj.jk.web.domain.UserFaceLog;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.mapper.UserBasicMapper;
import com.trj.jk.web.util.HttpClientUtils;
import com.trj.jk.web.util.JsonUtils;

@Service("cloudwalkFaceService")
public class CloudwalkFaceServiceImpl implements IFaceService {

    private static final Logger LOG = LoggerFactory.getLogger(CloudwalkFaceServiceImpl.class);

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    @Value("${app.upload.url}")
    private String uploadImgUrl;
    @Value("${app.remote.cloudwalk.domain}")
    private String cloudwalkDomain = null;
    @Value("${app.remote.cloudwalk.appid}")
    private String appid = null;
    @Value("${app.remote.cloudwalk.secret}")
    private String secret = null;
    @Value("${app.remote.cloudwalk.keyStorePath}")
    private String keyStorePath = null;
    @Value("${app.remote.cloudwalk.trustStorePath}")
    private String trustStorePath = null;
    @Value("${app.remote.cloudwalk.password}")
    private String password = null;
    @Value("${app.remote.cloudwalk.score}")
    private BigDecimal score = null;
    @Value("${app.remote.cloudwalk.idcardScore}")
    private BigDecimal idcardScore = null;
    @Resource
    private UserBasicMapper userBasicMapper;
    @Autowired
    private UserFaceLogService userFaceLogService;


    @Override
    public boolean compareFace(Integer uid, File img1, File img2) {
        Assert.notNull(img1, "图片1不能为空");
        Assert.notNull(img2, "图片2不能为空");
        boolean result = false;
        LOG.info("img1={},img2={}", img1.getName(), img2.getName());
        Map<String, String> contentMap = new HashMap<String, String>();
        try {
            String img1Str = convertFileToString(img1);
            String img2Str = convertFileToString(img2);
            contentMap.put("img1", img1Str);
            contentMap.put("img2", img2Str);
            Map<String, String> paramMap = getParamMap(contentMap);
            LOG.info("img1={},img2={}", img1Str, img2Str);
            String retJson = executePost(cloudwalkDomain + "/cweis/faceRecog/compareFace", null, paramMap, null, "UTF-8");
            LOG.info("retStr={}", retJson);
            retJson = decrypt(retJson, secret.toUpperCase().substring(8, 24));
            LOG.info("retJson={}", retJson);
            HashMap<String, String> retMap = (HashMap) JsonUtils.string2Object(retJson, HashMap.class);
            Object code = retMap.get("code");
            //调用信息入库
            UserFaceLog userFaceLog = new UserFaceLog();
            userFaceLog.setUid(uid);
            userFaceLog.setType((byte) 3);
            userFaceLog.setResult(retJson);
            if (StringUtils.equals("0", String.valueOf(code))) {
                Object retScore = retMap.get("score");
                if (idcardScore.compareTo(new BigDecimal(String.valueOf(retScore))) < 0) {
                    result = true;
                    userFaceLog.setStatus((byte) 1);
                    userFaceLog.setRemark("活体比对成功！");
                } else {
                    userFaceLog.setStatus((byte) 0);
                    userFaceLog.setRemark("活体比对失败！分数太低！");
                }
            } else {
                userFaceLog.setStatus((byte) 0);
                userFaceLog.setRemark("活体比对失败！");
            }
            userFaceLogService.insert(userFaceLog);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Result<Boolean> checkFaceImgPlus(Integer uid, String cardId, String name, File img) {
        try {
            //构建入参
            Map<String, String> contentMap = new HashMap<String, String>();
            contentMap.put("cId", cardId);
            contentMap.put("cName", name);
            String faceImg = convertFileToString(img);
            contentMap.put("img", faceImg);
            Map<String, String> paramMap = getParamMap(contentMap);

            LOG.info("cardId={},name={},img={},paramMap={}", cardId, name, img, paramMap.toString());
            String retJson = executePost(cloudwalkDomain + "/cweis/faceRecog/checkFace", null, paramMap, null, "UTF-8");
            LOG.info("retStr={}", retJson);
            retJson = decrypt(retJson, secret.toUpperCase().substring(8, 24));
            LOG.info("retJson={}", retJson);
            return CheckFaceImgEnum.validate(retJson,idcardScore);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }



    @Override
    public boolean checkFaceImg(Integer uid, String cardId, String name, File img) {
        boolean result = false;
        try {
            //构建入参
            Map<String, String> contentMap = new HashMap<String, String>();
            contentMap.put("cId", cardId);
            contentMap.put("cName", name);
            String faceImg = convertFileToString(img);
            contentMap.put("img", faceImg);
            Map<String, String> paramMap = getParamMap(contentMap);
            LOG.info("cardId={},name={},img={},paramMap={}", cardId, name, img, paramMap.toString());
            String retJson = executePost(cloudwalkDomain + "/cweis/faceRecog/checkFace", null, paramMap, null, "UTF-8");
            LOG.info("retStr={}", retJson);
            retJson = decrypt(retJson, secret.toUpperCase().substring(8, 24));
            LOG.info("retJson={}", retJson);
            HashMap<String, String> retMap = (HashMap) JsonUtils.string2Object(retJson, HashMap.class);
            Object code = retMap.get("code");
            Object retScore = retMap.get("score");
            //调用信息入库
            UserFaceLog userFaceLog = new UserFaceLog();
            userFaceLog.setUid(uid);
            userFaceLog.setType((byte) 1);
            userFaceLog.setResult(retJson);

            if (StringUtils.equals("0", String.valueOf(code))) {
                if (idcardScore.compareTo(new BigDecimal(String.valueOf(retScore))) < 0) {
                    result = true;
                    userFaceLog.setStatus((byte) 1);
                    userFaceLog.setRemark("身份证抠像比对成功！");
                } else {
                    userFaceLog.setStatus((byte) 0);
                    userFaceLog.setRemark("身份证抠像比对失败,分数太低");
                }
            } else if (StringUtils.equals("8464", String.valueOf(code))) {
                userFaceLog.setStatus((byte) 0);
                userFaceLog.setRemark("拍摄头像照片模糊,请重试");
            } else if(StringUtils.equals("65537", String.valueOf(code))){
                userFaceLog.setStatus((byte) 0);
                userFaceLog.setRemark("识别过程出现错误");
            }else{
                userFaceLog.setStatus((byte) 0);
                userFaceLog.setRemark("身份证抠像比对失败！");
            }
            userFaceLogService.insert(userFaceLog);

        } catch (RRException e){
            throw new RRException(e.getMsg());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return result;
    }


    @Override
    public boolean checkFaceEx(Integer uid, String cardId, String name, File img, File idcardImg) {
        UserBasicCriteria userBasicCriteria = new UserBasicCriteria();
        userBasicCriteria.createCriteria().andIdentityIdEqualTo(cardId).andIdNotEqualTo(uid);
        List<UserBasic> userBasics = userBasicMapper.selectByCriteria(userBasicCriteria);
        if (userBasics != null && !userBasics.isEmpty()) {
            throw new CheckException(ErrorMessageConstant.ERR_IDENTITYID_EXIST);
        }
        Assert.hasText(cardId, "身份证号码不能为空!");
        Assert.hasText(name, "姓名不能为空!");
        Assert.notNull(img, "图片不能为空");
        Assert.notNull(idcardImg, "图片不能为空");
        LOG.info("cardId={},name={},img={},idcardImg={}", cardId, name, img.getName(), idcardImg.getName());
        boolean result = false;
        try {
            String imgData = HttpClientUtils.uploadFile(new FileInputStream(img), img.getName(), uploadImgUrl);
            LOG.info("imgPath={}", imgData);
            String idCardData = HttpClientUtils.uploadFile(new FileInputStream(idcardImg), idcardImg.getName(), uploadImgUrl);
            LOG.info("idcardImgpath={}", idCardData);
            Map<String, String> contentMap = new HashMap<String, String>();
            contentMap.put("cId", cardId);
            contentMap.put("cName", name);
            String faceImg = convertFileToString(img);
            contentMap.put("img", faceImg);
            String idcardBase64Img = convertFileToString(idcardImg);
            contentMap.put("idcardImg", idcardBase64Img);
            Map<String, String> paramMap = getParamMap(contentMap);
            LOG.info("cardId={},name={},img={},idcardImg={},paramMap={}", cardId, name, img, idcardImg, paramMap.toString());
            String retJson = executePost(cloudwalkDomain + "/cweis/faceRecog/checkFaceEx", null, paramMap, null, "UTF-8");
            LOG.info("retStr={}", retJson);
            retJson = decrypt(retJson, secret.toUpperCase().substring(8, 24));
            LOG.info("retJson={}", retJson);
            HashMap<String, String> retMap = (HashMap) JsonUtils.string2Object(retJson, HashMap.class);
            Object code = retMap.get("code");
            if (StringUtils.equals("0", String.valueOf(code))) {
                Object retScore = retMap.get("score");
                Object retIdCardScor = retMap.get("idcardScore");
                if (score.compareTo(new BigDecimal(String.valueOf(retScore))) < 0 && idcardScore.compareTo(new BigDecimal(String.valueOf(retIdCardScor))) < 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return result;
    }


    private Map<String, String> getParamMap(Map<String, String> contentMap) throws Exception {
        Map<String, String> paramMap = new HashMap<String, String>();
        String content = JsonUtils.objectToJsonString(contentMap);
        String realSecret = secret.toUpperCase().substring(8, 24);
        paramMap.put("appid", appid);
        String sParam = encrypt(content, realSecret);
        paramMap.put("sParam", sParam);
        String lol = digest(sParam, "MD5");
        paramMap.put("lol", lol);
        long millis = System.currentTimeMillis();
        String signature = generateSignature(appid, lol, millis);
        paramMap.put("signature", signature);
        paramMap.put("timestamp", "" + millis);
        return paramMap;
    }


    private String executePost(String url, String postData, Map<String, String> paraMap, Map<String, String> reqHeadMap, String encodeCharset) {
        String responseBody = null;
        CloseableHttpClient client = null;
        HttpPost post = null;

        try {
            // 创建HttpClient客户端

            KeyStore truststore = KeyStore.getInstance(KeyStore.getDefaultType());

            FileInputStream truststoreFile = new FileInputStream(new ClassPathResource(keyStorePath).getFile());

            truststore.load(truststoreFile, password.toCharArray());

            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(truststore, new TrustSelfSignedStrategy()).build();

            client = HttpClients.custom().setSslcontext(sslContext).setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

            post = new HttpPost(url);
            // 设定头部信息
            if (reqHeadMap != null) {
                for (String key : reqHeadMap.keySet()) {
                    post.addHeader(key, reqHeadMap.get(key));
                }
            }

            if (!StringUtils.isEmpty(postData)) {
                post.setEntity(new StringEntity(postData, encodeCharset == null ? "UTF-8" : encodeCharset));
            } else {
                if (paraMap != null) {
                    List<NameValuePair> formParams = new ArrayList<NameValuePair>();
                    for (Map.Entry<String, String> entry : paraMap.entrySet()) {
                        formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                    }
                    post.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset == null ? "UTF-8" : encodeCharset));

                }
            }
            CloseableHttpResponse response = client.execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                if (responseCode == HttpStatus.SC_OK) {
                    if (entity != null) {
                        responseBody = EntityUtils.toString(entity, "UTF-8");
                    }
                } else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
                    return null;
                }
            }

            if (StringUtils.isNotBlank(responseBody)) {
                return responseBody;
            }
        } catch (Exception e) {
            LOG.error("Error occurred when send HttpPost: " + url, e);
        } finally {
            if (post != null && !post.isAborted()) {
                post.abort();
            }
            post.releaseConnection();

            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseBody;
    }

    public static String convertFileToString(File img) {
        String result = null;
        FileInputStream fis = null;
        byte[] imgByte = null;
        try {
            fis = new FileInputStream(img);
            imgByte = new byte[(int) img.length()];
            fis.read(imgByte);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        if (imgByte != null && imgByte.length > 0) {
            result = Base64.encodeBase64String(imgByte);
        }
        return result;
    }

    public String encrypt(String content, String key) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        int blockSize = cipher.getBlockSize();
        byte[] dataBytes = content.getBytes("UTF-8");
        int plaintextLength = dataBytes.length;
        if (plaintextLength % blockSize != 0) {
            plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
        }

        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        IvParameterSpec ivspec = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);

        byte[] encrypted = cipher.doFinal(plaintext);
        return Base64.encodeBase64String(encrypted).replaceAll("\\s+|\n|\r|\t", "");
    }

    private String digest(String strSrc, String encName) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes("UTF-8");
        if (encName == null || encName.equals("")) {
            encName = "MD5";
        }
        md = MessageDigest.getInstance(encName);
        md.update(bt);
        strDes = bytesToHexString(md.digest()); // to HexString
        return strDes;
    }

    private String bytesToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    private String generateSignature(String appid, String lol, long millis) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String timestamp = String.valueOf(millis);
        String signature = null;
        if (!StringUtils.isEmpty(timestamp) && !StringUtils.isEmpty(appid)) {
            List<String> srcList = new ArrayList<String>();
            srcList.add(timestamp);
            srcList.add(appid);
            srcList.add(lol);
            // 按照字典序逆序拼接参数
            Collections.sort(srcList);
            Collections.reverse(srcList);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < srcList.size(); i++) {
                sb.append(srcList.get(i));
            }
            signature = digest(sb.toString(), "SHA-1");
            srcList.clear();
            srcList = null;
        }
        return signature;
    }

    private String decrypt(String content, String key) throws Exception {

        String ret = null;
        byte[] encrypted = Base64.decodeBase64(content);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");

        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        IvParameterSpec ivspec = new IvParameterSpec(key.getBytes("UTF-8"));

        cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
        byte[] original = cipher.doFinal(encrypted);
        ret = new String(original);
        return ret.trim();
    }



    @Override
    public Map<String, String> idCardOcr(Integer uid, String idcardBase64Img) {
        Map<String, String> result = null;
        Assert.hasText(idcardBase64Img, "身份证正面图片不能为空");
        try {
            Map<String, String> paramMap = new HashMap<String, String>();
            Map<String, String> contentMap = new HashMap<String, String>();
            contentMap.put("img", idcardBase64Img);
            String content = JsonUtils.objectToJsonString(contentMap);

            String realSecret = secret.toUpperCase().substring(8, 24);
            paramMap.put("appid", appid);
            String sParam = encrypt(content, realSecret);
            paramMap.put("sParam", sParam);
            String lol = digest(sParam, "MD5");
            paramMap.put("lol", lol);
            long millis = System.currentTimeMillis();
            String signature = generateSignature(appid, lol, millis);
            paramMap.put("signature", signature);
            paramMap.put("timestamp", "" + millis);

            String url = cloudwalkDomain + "/cweis/faceRecog/idCardOcr";

            LOG.info("url={},paramMap={}", url, paramMap);
            String retJson = executePost(cloudwalkDomain + "/cweis/faceRecog/idCardOcr", null, paramMap, null, "UTF-8");
            LOG.info("retStr={}", retJson);
            retJson = decrypt(retJson, realSecret);
            LOG.info("retJson={}", retJson);

            HashMap<String, String> retMap = (HashMap) JsonUtils.string2Object(retJson, HashMap.class);
            Object code = retMap.get("code");
            if (StringUtils.equals("0", String.valueOf(code))) {
                result = retMap;
                //调用信息入库
                UserFaceLog userFaceLog = new UserFaceLog();
                userFaceLog.setStatus((byte) 1);
                userFaceLog.setType((byte) 0);
                userFaceLog.setUid(uid);
                userFaceLog.setResult(retJson);
                userFaceLog.setRemark("身份证ocr识别成功！");
                userFaceLogService.insert(userFaceLog);
            }

        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            throw new RuntimeException("身份证ocr识别失败！" + ex.getMessage());
        }
        return result;
    }

}
