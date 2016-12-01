package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebeksamenApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Autowired
	FotoRepository fotoRepository;

	@Test
	public void testFotoDB() {
		Foto foto = new Foto("Tittel", "FotoID");
		Foto fotoRetur = fotoRepository.save(foto);
		System.out.println(fotoRetur.getId());
		Assert.assertNotNull(fotoRetur.getId());
	}
}
