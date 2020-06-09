package com.william.bc_mall_server;

import com.william.bc_mall_server.redis.RedisService;
import com.william.utils.IdSaltUtils;
import com.william.utils.Md5Util;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BcMallServerApplicationTests {

	@Autowired
	private RedisService redisService;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {

	}


	@Test
	public void passwordAndSalt(){
		ByteSource salt1 = IdSaltUtils.getSalt("18701192829", "1");
		System.out.println(salt1);
		String md5 = Md5Util.md5("123");
		System.out.println(md5);
		String s = new Md5Hash("123", salt1, 2).toString();
		System.out.println(s);
	}
}
