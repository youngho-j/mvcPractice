package com.spring.shop.mapper;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/test-root-context.xml")
public class AuthorMapperTest {
	
	@Autowired
	private AuthorMapper authorMapper;
	
	private AuthorVO author1;
	private AuthorVO author2;
	private AuthorVO author3;
	
	@Before
	public void setUp() {
		author1 = new AuthorVO();
		author2 = new AuthorVO();
		author3 = new AuthorVO();
		
		
		author1.setNationId("01");
		author1.setAuthorName("테스트1");
		author1.setAuthorProfile("테스터입니다.");
		
		author2.setNationId("02");
		author2.setAuthorName("테스트2");
		author2.setAuthorProfile("테스터입니다.");
		
		author3.setNationId("23534654365443653968450968402437329847");
		author3.setAuthorName("테스트1");
		author3.setAuthorProfile("테스터입니다.");
	}
	
	@Test
	public void getCount_메서드_테스트() throws Exception {
		authorMapper.deleteAll();
		assertThat(authorMapper.getCount(), is(0));
		
		authorMapper.authorEnroll(author1);
		assertThat(authorMapper.getCount(), is(1));
		
		authorMapper.authorEnroll(author2);
		assertThat(authorMapper.getCount(), is(2));
	}
	
	@Test
	public void 작가_등록_메서드_테스트() throws Exception {
		authorMapper.deleteAll();
		
		assertThat(authorMapper.getCount(), is(0));
		
		int result = authorMapper.authorEnroll(author1);
		
		assertThat(1, is(result));
		
		assertThat(authorMapper.getCount(), is(1));
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void 작가_등록시_컬럼값_초과_예외처리_테스트() throws Exception {
		
		authorMapper.deleteAll();
		
		assertThat(authorMapper.getCount(), is(0));
		
		authorMapper.authorEnroll(author3);
	}

	@Test
	public void 작가목록_출력_메서드_테스트() throws Exception {
		PageInfo paging = new PageInfo(1, 10);
		
		authorMapper.deleteAll();
		
		assertThat(authorMapper.getCount(), is(0));
		
		authorMapper.authorEnroll(author1);
		authorMapper.authorEnroll(author2);
		
		assertThat(authorMapper.getCount(), is(2));
		
		List<AuthorVO> list = authorMapper.authorGetList(paging);		
		
		assertFalse(list.isEmpty());
		assertThat(list.size(), is(2));
	}
	
	@Test
	public void 등록된_작가수_카운팅_메서드_테스트() throws Exception {
		PageInfo paging = new PageInfo();
		
		authorMapper.deleteAll();
		
		assertThat(authorMapper.getCount(), is(0));
		
		authorMapper.authorEnroll(author1);
		authorMapper.authorEnroll(author2);
		
		int total = authorMapper.authorGetTotal(paging);
		
		assertThat(total, is(2));;
	}
	
	@Test
	public void 작가_상세_정보_메서드_테스트() throws Exception {
		authorMapper.deleteAll();
		
		assertThat(authorMapper.getCount(), is(0));
		
		authorMapper.authorEnroll(author1);
		
		AuthorVO authorDetail = authorMapper.authorGetDetail(authorMapper.getLastPK());
		
		assertThat(author1.getAuthorName(), is(authorDetail.getAuthorName()));
	}
	
	@Test
	public void 작가_정보_수정_메서드_테스트() throws Exception {
		authorMapper.deleteAll();
		
		assertThat(authorMapper.getCount(), is(0));
		
		authorMapper.authorEnroll(author1);
		
		int primaryKey = authorMapper.getLastPK();
		
		AuthorVO authorDetail = authorMapper.authorGetDetail(primaryKey);
		
		author2.setAuthorId(primaryKey);
		
		int result = authorMapper.authorModify(author2);
		
		AuthorVO ModifyDetail = authorMapper.authorGetDetail(primaryKey);
		
		assertThat(result, is(1));
		assertNotEquals(authorDetail.getAuthorName(), ModifyDetail.getAuthorName());
	}
	
	@Test
	public void 작가_정보_삭제_메서드_테스트() throws Exception {
		authorMapper.deleteAll();
		
		assertThat(authorMapper.getCount(), is(0));
		
		authorMapper.authorEnroll(author1);
		
		int primaryKey = authorMapper.getLastPK();
		
		int result = authorMapper.authorDelete(primaryKey);
		
		assertThat(result, is(1));
		
		AuthorVO authorDetail = authorMapper.authorGetDetail(primaryKey);
		
		assertNull(authorDetail);
	}
	
	@Test
	public void 작가_등록책수_출력_메서드_테스트() throws Exception {
		authorMapper.deleteAll();
		
		assertThat(authorMapper.getCount(), is(0));
		
		authorMapper.authorEnroll(author1);
		
		int primaryKey = authorMapper.getLastPK();
		
		int result = authorMapper.authorWrittenBook(primaryKey);
		
		assertThat(result, is(0));
	}
}
