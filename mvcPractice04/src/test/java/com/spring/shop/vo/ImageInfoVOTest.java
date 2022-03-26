package com.spring.shop.vo;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

public class ImageInfoVOTest {

	@Test
	public void ImageInfoVO_테스트() {
		
		ImageInfoVO imageInfo = 
				new ImageInfoVO
				.Builder()
				.uploadPath("C:\\work")
				.uuid("121kfd-g43")
				.fileName("book2.jpg")
				.build();
		
		assertNotNull(imageInfo);
		assertThat("book2.jpg", is(imageInfo.getFileName()));
	}

}
