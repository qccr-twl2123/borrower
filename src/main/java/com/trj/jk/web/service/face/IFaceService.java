package com.trj.jk.web.service.face;

import com.trj.commons.result.Result;

import java.io.File;
import java.util.Map;

/**
 * 人脸识别相关接口
 *
 */
public interface IFaceService {

	/**
	 * 人,证,公安 三像比对 
	 * @param cardId:身份证号
	 * @param name：姓名
	 * @param img：活体照
	 * @param idcardImg：身份证抠像
	 * @return
	 */
	 boolean checkFaceEx(Integer uid, String cardId, String name, File img, File idcardImg);
	/**
	 * 身份证orc读取身份信息
	 * @param idcardBase64Img:身份证照片（base64）
	 * @return
	 */
	 Map<String, String> idCardOcr(Integer uid, String idcardBase64Img);
	/**
	 * 联网核查人像
	 * 比对是客户身份证号码、姓名和待比对的人脸图片
	 * 台平根据身份证号码和姓名到公安系统联网核查获得 公安人 像数据，
	 * 然后和待比对人脸图片进行人相似度 比对，从而返回像脸图片进行人相似度 比对，从而返回人像比对的相识度。
	 * @param cardId
	 * @param name
	 * @param img
	 * @return
	 */
	 boolean checkFaceImg(Integer uid, String cardId, String name, File img);
	/**
	 * 图像比对（两张图片进行比对返回相识度）
	 * @param img1
	 * @param img2
	 * @return
	 */
	 boolean compareFace(Integer uid, File img1, File img2);

	/**
	 * 联网核查人像
	 * 比对是客户身份证号码、姓名和待比对的人脸图片
	 * 台平根据身份证号码和姓名到公安系统联网核查获得 公安人 像数据，
	 * 然后和待比对人脸图片进行人相似度 比对，从而返回像脸图片进行人相似度 比对，从而返回人像比对的相识度。
	 * @param cardId
	 * @param name
	 * @param img
	 * @return
	 */
	 Result<Boolean> checkFaceImgPlus(Integer uid, String cardId, String name, File img);

}
