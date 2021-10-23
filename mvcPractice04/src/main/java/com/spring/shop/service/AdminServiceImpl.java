package com.spring.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.AdminMapper;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.CategoryVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public int bookEnroll(BookVO bookVO) throws Exception {
		log.info("책 등록 service 실행");
		
		return adminMapper.bookEnroll(bookVO);
	}

	@Override
	public List<CategoryVO> categoryList() throws Exception {
		return adminMapper.categoryList();
	}
	
	
}
