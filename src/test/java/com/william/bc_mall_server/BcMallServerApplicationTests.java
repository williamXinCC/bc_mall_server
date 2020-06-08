package com.william.bc_mall_server;

import com.william.bc_mall_server.redis.RedisService;
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


}
