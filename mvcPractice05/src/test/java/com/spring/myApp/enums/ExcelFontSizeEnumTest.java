package com.spring.myApp.enums;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class ExcelFontSizeEnumTest {

	@Test
	public void ExcelSizeOption_enum값_조회() throws Exception {
		assertThat(ExcelFontSize.FIFTEEN.getValue(), is((short)15));
	}
}
