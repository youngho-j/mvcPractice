package com.spring.shop.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.spring.shop.vo.ImageInfoVO;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileManager {
	
	private final File file;
	
	@Getter
	private final String Absolutepath;
	
	public FileManager(String uploadRoot) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String stringDate = dateFormat.format(new Date());
		
		// 윈도우에서 File.separator 사용시 \\ 을 -> \ 로 리턴하여 character to be escaped is missing 에러 발생
		// 따라서 Matcher 클래스의 quoteReplacement(String s)를 사용
		String detailRoot = stringDate.replaceAll("-", Matcher.quoteReplacement(File.separator));
		
		this.file = new File(uploadRoot, detailRoot);
		this.Absolutepath = this.file.getAbsolutePath();
	}
	
	public boolean createFolder() throws Exception {
		if(!file.exists()) {
			log.info("폴더 생성");
			return file.mkdirs();
		}
		log.info("이미 폴더가 생성되었습니다.");
		return false;
	}
	
	public List<ImageInfoVO> transferToFolder(MultipartFile[] multipartFile, String uploadRoot) throws Exception {
		
		List<ImageInfoVO> imageList = new ArrayList<ImageInfoVO>();
		
		StringBuilder sb = new StringBuilder();
		
		for(MultipartFile file : multipartFile) {
			String uploadFileName = file.getOriginalFilename();
			
			// 파일 이름 중복을 막기위해 UUID 사용
			String uuid = UUID.randomUUID().toString();
			
			// 이미지 정보를 담은 객체
			ImageInfoVO imageInfo = new ImageInfoVO.Builder(1)
					.uploadPath(uploadRoot)
					.uuid(uuid)
					.fileName(uploadFileName)
					.build();
			
			sb.append(uuid);
			sb.append("_");
			sb.append(uploadFileName);
			
			uploadFileName = sb.toString();
			
			// 변경된 파일 이름과 해당년월일자 폴더 경로를 갖는 file 객체 생성
			File saveFile = new File(uploadRoot, uploadFileName);
			
			// 수신 파일 객체(file)를 목적지 파일 객체(savefile)로 전달하여 저장
			file.transferTo(saveFile);
			
			// 썸네일 생성 및 저장
			saveThumbnail(sb, uploadRoot, saveFile);
			
			imageList.add(imageInfo);
			
			log.info("파일 저장 완료!");
			
			// 길이를 0으로 설정하여 StringBuilder 초기화 
			sb.setLength(0);
			
		}
		return imageList;
	}
	
	public void saveThumbnail(StringBuilder sb, String uploadRoot, File saveFile) throws Exception {
		// 기존 작성된 파일명 앞에 추가 
		sb.insert(0, "t_");
		
		double scaleDown = 3;
		
		String thumbnailSaveFileName = sb.toString();
		
		File thumbnailFile = new File(uploadRoot, thumbnailSaveFileName);
		
		// 기존 파일 객체를 BufferedImage 객체로 변환 (썸네일로 만들기 위한 전 작업)
		BufferedImage originImage = ImageIO.read(saveFile);
		
		// 원본 사진의 높이와 너비 길이 축소 
		int width = convertSize(originImage.getWidth(), scaleDown);
		int height = convertSize(originImage.getHeight(), scaleDown);
		
		// 썸네일 이미지 너비, 높이, 이미지 타입 설정된 객체 생성
		BufferedImage thumbImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		
		// BufferedImage 객체에 그림을 그리기 위해 객체 생성
		Graphics2D graphic = thumbImage.createGraphics();
		
		// 설정된 크기의 영역에 첫번째 인자로 받은 이미지를 그림
		graphic.drawImage(originImage, 0, 0, width, height, null);
		
		ImageIO.write(thumbImage, "jpg", thumbnailFile);
		log.info("썸네일 파일 저장 완료!");
	}
	
	private int convertSize(int size, double scaleDown) throws Exception {
		return (int) (size / scaleDown);
	}
}
