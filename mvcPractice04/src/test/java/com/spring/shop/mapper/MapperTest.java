package com.spring.shop.mapper;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MapperTest {
		
		@Autowired
		private ShopMapper shopMapper;
	
		@Test
		public void MappperInterface_테스트() throws Exception {
			log.info(shopMapper.getTime());
		}
		
		@Test
		public void Mappper_xml_테스트() throws Exception {
			log.info(shopMapper.getTime2());
		}
}
