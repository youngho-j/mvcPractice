package com.spring.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.shop.mapper.AdminMapper;
import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.CategoryVO;
import com.spring.shop.vo.ImageInfoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;
	
	@Transactional
	@Override
	public int bookEnroll(BookVO bookVO) throws Exception {
		log.info("책 등록 service 실행");
		
		ImageInfoVO imageInfo = null;
		
		// 이미지 정보 등록 변수
		int imageResult = 0;
		
		// 상품 정보 등록 변수
		int bookResult = adminMapper.bookEnroll(bookVO);
		
		// 이미지 정보가 들어있지 않은 경우 
		if(bookVO.getImagesList() == null || bookVO.getImagesList().size() <= 0) {
			return bookResult;
		}
		
		for(int i = 0 ; i < bookVO.getImagesList().size() ; i++) {
			imageInfo = new ImageInfoVO.Builder()
					.bookId(bookVO.getBookId())
					.uploadPath(bookVO.getImagesList().get(i).getUploadPath())
					.uuid(bookVO.getImagesList().get(i).getUuid())
					.fileName(bookVO.getImagesList().get(i).getFileName())
					.build();
			
			imageResult += adminMapper.imageEnroll(imageInfo);
		}
		
		// 상품 정보 등록이 되지 않았거나 이미지 등록이 되지 않은 경우
		if(bookResult < 1 || imageResult <= 0) {
			return 0;
		}
		
		return bookResult + imageResult;
	}

	@Override
	public List<CategoryVO> categoryList() throws Exception {
		return adminMapper.categoryList();
	}

	@Override
	public List<BookVO> goodsList(PageInfo pageInfo) throws Exception {
		return adminMapper.goodsList(pageInfo);
	}

	@Override
	public int goodsTotal(PageInfo pageInfo) throws Exception {
		return adminMapper.goodsTotal(pageInfo);
	}

	@Override
	public BookVO goodsDetail(int bookId) throws Exception {
		return adminMapper.goodsDetail(bookId);
	}

	@Override
	public int goodsModify(BookVO bookVO) throws Exception {
		return adminMapper.goodsModify(bookVO);
	}
	
	@Override
	public int goodsDelete(int bookId) throws Exception {
		return adminMapper.goodsDelete(bookId);
	}
}
