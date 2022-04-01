package com.spring.shop.mapper;

import static org.junit.Assert.*;

import org.junit.After;

import static org.hamcrest.core.Is.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.vo.AuthorVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/test-root-context.xml")
public class AuthorMapperTest {
	
	@Autowired
	private AuthorMapper authorMapper;
	
	private static AuthorVO localAuthorInfo;
	private static AuthorVO foreignAuthorInfo;
	private static AuthorVO wrongAuthorInfo;
	
	@BeforeClass
	public static void setUp() {
		localAuthorInfo = new AuthorVO();
		foreignAuthorInfo = new AuthorVO();
		wrongAuthorInfo = new AuthorVO();
		
		localAuthorInfo.setNationId("01");
		localAuthorInfo.setAuthorName("테스트1");
		localAuthorInfo.setAuthorProfile("테스터입니다.");
		
		foreignAuthorInfo.setNationId("02");
		foreignAuthorInfo.setAuthorName("테스트2");
		foreignAuthorInfo.setAuthorProfile("테스터입니다.");
		
		wrongAuthorInfo.setNationId("23534654365443653968450968402437329847");
		wrongAuthorInfo.setAuthorName("테스트1");
		wrongAuthorInfo.setAuthorProfile("테스터입니다.");
	}
	
	@Before
	public void beforeMethod() {
		authorMapper.deleteAll();		
		assertThat(authorMapper.getCount(), is(0));
	}
	
	@After
	public void afterMethod() {
		authorMapper.deleteAll();		
		assertThat(authorMapper.getCount(), is(0));
	}
	
	@Test
	public void getCount_메서드_테스트() throws Exception {
		authorMapper.authorEnroll(localAuthorInfo);
		assertThat(authorMapper.getCount(), is(1));
		
		authorMapper.authorEnroll(foreignAuthorInfo);
		assertThat(authorMapper.getCount(), is(2));
	}
	
	@Test
	public void 작가_등록_메서드_테스트() throws Exception {
		int result = authorMapper.authorEnroll(localAuthorInfo);
		
		assertThat(result, is(1));
		
		assertThat(authorMapper.getCount(), is(1));
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void 작가_등록시_컬럼값_초과_예외처리_테스트() throws Exception {
		authorMapper.authorEnroll(wrongAuthorInfo);
	}

	@Test
	public void 작가_상세_정보_메서드_테스트() throws Exception {
		authorMapper.authorEnroll(localAuthorInfo);
		
		AuthorVO authorDetail = authorMapper.authorGetDetail(authorMapper.getLastPK());
		
		assertThat(localAuthorInfo.getAuthorName(), is(authorDetail.getAuthorName()));
	}
	
	@Test
	public void 작가_정보_수정_메서드_테스트() throws Exception {
		authorMapper.authorEnroll(localAuthorInfo);
		
		int primaryKey = authorMapper.getLastPK();
		
		AuthorVO authorDetail = authorMapper.authorGetDetail(primaryKey);
		
		foreignAuthorInfo.setAuthorId(primaryKey);
		
		int result = authorMapper.authorModify(foreignAuthorInfo);
		
		AuthorVO ModifyDetail = authorMapper.authorGetDetail(primaryKey);
		
		assertThat(result, is(1));
		assertNotEquals(authorDetail.getAuthorName(), ModifyDetail.getAuthorName());
	}
	
	@Test
	public void 작가_정보_삭제_메서드_테스트() throws Exception {
		authorMapper.authorEnroll(localAuthorInfo);
		
		int primaryKey = authorMapper.getLastPK();
		
		int result = authorMapper.authorDelete(primaryKey);
		
		assertThat(result, is(1));
		
		AuthorVO authorDetail = authorMapper.authorGetDetail(primaryKey);
		
		assertNull(authorDetail);
		
		assertThat(authorMapper.getCount(), is(0));
	}
	
	@Test
	public void 작가_등록책수_출력_메서드_테스트() throws Exception {
		authorMapper.authorEnroll(localAuthorInfo);
		
		int primaryKey = authorMapper.getLastPK();
		
		int result = authorMapper.authorWrittenBook(primaryKey);
		
		assertThat(result, is(0));
	}
}
