package com.spring.myApp.enums;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class SearchTermsEnumTest {

	@Test
	public void SearchTerms_enum값_조회() throws Exception {
		assertThat(SearchTerms.War.getKeyword(), is("전쟁"));
	}
}
