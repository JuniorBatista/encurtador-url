package com.desafio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.desafio.DesafioEncurtadorApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DesafioEncurtadorApplication.class)
@TestPropertySource(locations="classpath:test.properties")
public class DesafioEncurtadorApplicationTests {

	@Test
	public void contextLoads() {
	}

}
