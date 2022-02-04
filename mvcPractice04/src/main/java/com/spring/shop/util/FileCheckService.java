package com.spring.shop.util;

import java.util.List;

public interface FileCheckService {
	
	// 폴더 저장 이미지 파일 목록 리턴
	public List<String> getListOfFilesInFolder() throws Exception;
	
	// 삭제할 파일 목록 리턴
	public List<String> getUnknownFiles(List<String> dbImageList, List<String> folderImageList) throws Exception;
	
	// 파일 삭제
	public void deleteFilesInFolder(List<String> deleteImageList) throws Exception;
}
