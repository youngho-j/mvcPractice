package com.spring.shop.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.spring.shop.vo.ImageInfoVO;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class FileManager2 {

	// 고정 경로
	private final String fixedPath;

	// 변동 경로
	private final String variationPath;

	// 파일 이름
	private final String fileName;
	
	private FileManager2(Builder builder) {
		this.fixedPath = builder.fixedPath;
		this.variationPath = builder.variationPath;
		this.fileName = builder.fileName;
	}
	
	public static class Builder {

		private String fixedPath;
		
		private String variationPath = "";

		private String fileName = "";

		public Builder(String fixedPath) {
			this.fixedPath = fixedPath;
		}

		public Builder variationPath(String variationPath) {
			this.variationPath = variationPath;
			return this;
		}

		public Builder fileName(String fileName) {
			this.fileName = fileName;
			return this;
		}

		public FileManager2 build() {
			return new FileManager2(this);
		}
	}
	
	public boolean createFolder() throws Exception {
		File folder = new File(fixedPath, variationPath);
		
		if (!folder.exists()) {
			log.info("폴더 생성");
			return folder.mkdirs();
		}
		log.info("이미 폴더가 생성되었습니다.");
		return false;
	}

	public List<ImageInfoVO> getSavedImagefileList(MultipartFile[] multipartFile, String uploadRoot) throws Exception {

		List<ImageInfoVO> imageList = new ArrayList<ImageInfoVO>();

		StringBuilder imageFileName = new StringBuilder();

		for (MultipartFile file : multipartFile) {
			String uploadFileName = file.getOriginalFilename();

			// 파일 이름 중복을 막기위해 UUID 사용
			String uuid = UUID.randomUUID().toString();

			// 이미지 정보를 담은 객체
			ImageInfoVO imageInfo = 
					new ImageInfoVO.Builder()
					.uploadPath(uploadRoot).uuid(uuid)
					.fileName(uploadFileName)
					.build();

			imageFileName.append(uuid);
			imageFileName.append("_");
			imageFileName.append(uploadFileName);

			String convertUploadFileName = imageFileName.toString();
			
			// 변경된 파일 이름과 해당년월일자 폴더 경로를 갖는 file 객체 생성
			File saveFile = new File(uploadRoot, convertUploadFileName);
			
			// 이미지 파일 업로드 폴더 내 저장
			saveImageFile(file, saveFile);
			
			// 썸네일 생성 및 저장
			saveThumbnail(imageFileName, uploadRoot, saveFile);

			imageList.add(imageInfo);

			log.info("파일 저장 완료!");

			// 길이를 0으로 설정하여 StringBuilder 초기화
			imageFileName.setLength(0);

		}
		return imageList;
	}
	
	public void saveImageFile(MultipartFile file, File destFile) throws Exception {
		
		// 수신한 멀티파트파일 객체(file)를 목적지 파일 객체(savefile)로 전달하여 저장
		file.transferTo(destFile);			
		
	}
	
	public void saveThumbnail(StringBuilder sb, String uploadRoot, File saveFile) throws Exception {
		double scaleDown = 3;
		
		// 기존 작성된 파일명 앞에 추가
		sb.insert(0, "t_");

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

	public boolean MIMETYPECheck(MultipartFile[] multipartFile) throws Exception {
		for (MultipartFile file : multipartFile) {

			Path filePath = new File(file.getOriginalFilename()).toPath();

			String mimeType = Files.probeContentType(filePath);
			log.info("파일 MIME TYPE : " + mimeType);

			if (!mimeType.startsWith("image")) {
				return false;
			}

		}
		return true;
	}
	
	public boolean deleteImageFile() {
		File file = null;
		
		try {
			// 썸네일 객체 생성 / fileName -> 유동경로 + UUID + 파일 이름
			file = new File(fixedPath, URLDecoder.decode(fileName, "UTF-8"));
			
			// 원본 파일 경로
			String originFilePath = file.getAbsolutePath().replace("t_", "");

			if(file.exists()) {
				
				// 썸네일 삭제
				file.delete();
				
				// 원본 파일 객체 생성
				file = new File(originFilePath);
				
				// 원본 파일 삭제
				file.delete();

				return true;
			}
			
			// 파일 또는 디렉토리가 존재하지 않을 경우
			return false;		
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private int convertSize(int size, double scaleDown) throws Exception {
		return (int) (size / scaleDown);
	}
}
