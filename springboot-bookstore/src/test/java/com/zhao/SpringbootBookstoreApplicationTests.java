package com.zhao;

import com.zhao.mapper.ProductMapper;
import com.zhao.mapper.UserMapper;
import com.zhao.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@SpringBootTest
class SpringbootBookstoreApplicationTests {

	@Autowired
	private ProductMapper productMapper;

	@Test
	public void testSelect() {
//		MessageDigest md5 = MessageDigest.getInstance("MD5");
//		md5.update("222".getBytes());
//		System.out.println(new BigInteger(1,md5.digest()).toString(16));
//		int count = productMapper.count(null);
//		System.out.println(count);
		int java = productMapper.countByName("java");
		System.out.println(java);
	}
}
