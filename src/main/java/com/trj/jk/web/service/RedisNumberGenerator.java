package com.trj.jk.web.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;


@Component
public class RedisNumberGenerator implements NumberGenerator {

	@Resource
	private RedisTemplate<String, Long> redisTemplate;

	@Override
	public String generateNumber(String prefix, int digit) {
		StringBuilder sb = new StringBuilder();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String key = dateFormat.format(date);
		if (prefix != null && prefix.trim().length() > 0) {
			key = prefix.trim() + key;
		}
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		long number = redisTemplate.boundHashOps(key).increment(key,1);
		if (number == 1) {
			redisTemplate.expire(key, 1, TimeUnit.DAYS);
		}
		sb.append(key);
		String numStr = String.valueOf(number);
		if (numStr.length() < digit) {
			for (int i = 0; i < digit - numStr.length(); i++) {
				sb.append("0");
			}
		}
		sb.append(numStr);
		return sb.toString();
	}



}
