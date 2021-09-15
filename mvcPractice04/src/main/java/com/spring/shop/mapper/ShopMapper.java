package com.spring.shop.mapper;

import org.apache.ibatis.annotations.Select;

public interface ShopMapper {
	
	@Select("SELECT sysdate() FROM dual")
	public String getTime();
	
	public String getTime2();
}
