package com.spring.shop.service;

public interface TestMethodService {
	// 테이블 전체 삭제(테스트용)
	default void deleteAll() {
		return;
	}
			
	// 테이블 전체 삭제 검증(테스트용)
	default int getCount() {
		return 0;
	}
		
	// 마지막으로 등록한 상품 정보(테스트용)
	default int getLastPK() {
		return 0;
	}
}
