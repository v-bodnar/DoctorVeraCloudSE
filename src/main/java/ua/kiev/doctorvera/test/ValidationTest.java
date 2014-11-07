package ua.kiev.doctorvera.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.kiev.doctorvera.logic.Validator;

public class ValidationTest {
	
	@Test
	public void testPhoneNumbers() {
		assertEquals(Validator.checkPhone("+380635744511"),"");
		assertEquals(Validator.checkPhone("063 574 45 11"),"");
		assertEquals(Validator.checkPhone("+3 8063 574 45 11"),"");
		assertEquals(Validator.checkPhone("(063) 574-45-11"),"");
		assertEquals(Validator.checkPhone("+3 8(063) 574-45-11"),"");	
		assertFalse(Validator.checkPhone("635744511").equals(""));
		assertFalse(Validator.checkPhone("asdad0635744511").equals(""));
	}
	
	@Test
	public void testEmail() {
		assertFalse(Validator.checkEmail("@ear.asd").equals(""));
		assertFalse(Validator.checkEmail("test@ear").equals(""));
		assertFalse(Validator.checkEmail("testear.asd").equals(""));
		assertEquals(Validator.checkEmail("test@ear.asd"),"");
	}
	
	@Test
	public void testName() {
		assertFalse(Validator.checkName("asdasdf"), Validator.checkName("asdasdf").equals(""));
		assertFalse(Validator.checkName("asdaf545sdf"), Validator.checkName("asdaf545sdf").equals(""));
		assertFalse(Validator.checkName("asdas df"), Validator.checkName("asdas df").equals(""));
		assertFalse(Validator.checkName("asda .sdf"), Validator.checkName("asda .sdf").equals(""));
		assertFalse(Validator.checkName("asdam.sdf"), Validator.checkName("asdam.sdf").equals(""));
		assertFalse(Validator.checkName("вапвпвsdfsfп"), Validator.checkName("вапвпвsdfsfп").equals(""));
		assertEquals(Validator.checkName("Фыв"), Validator.checkName("Фыв"),"");
	}
	
	@Test
	public void testPassword() {
		assertFalse(Validator.checkPassword("asdasdf"), Validator.checkPassword("asdasdf").equals(""));
		assertFalse(Validator.checkPassword("zxc3"), Validator.checkPassword("zxc3").equals(""));
		assertFalse(Validator.checkPassword("авпвап"), Validator.checkPassword("авпвап").equals(""));
		assertEquals(Validator.checkPassword("dfgdg354"), Validator.checkPassword("dfgdg354"),"");
	}
	
	@Test
	public void testUsername() {
		//assertFalse(Validator.checkUsername("bodnar"), Validator.checkUsername("bodnar").equals(""));
		assertFalse(Validator.checkUsername("авпsaaвап"), Validator.checkUsername("авпsaaвап").equals(""));
		assertFalse(Validator.checkUsername("ddgfh."), Validator.checkUsername("ddgfh.").equals(""));
		assertEquals(Validator.checkUsername("dfgdg354"), Validator.checkUsername("dfgdg354"),"");
		assertEquals(Validator.checkUsername("asdad"), Validator.checkUsername("asdad"),"");
	}
	
	@Test
	public void testNumeric() {
		assertFalse(Validator.checkNumeric("bodnar"), Validator.checkNumeric("bodnar").equals(""));
		assertFalse(Validator.checkNumeric("asdf32"), Validator.checkNumeric("asdf32").equals(""));
		assertFalse(Validator.checkNumeric("авпsaaвап"), Validator.checkNumeric("авпsaaвап").equals(""));
		assertFalse(Validator.checkNumeric("ddgfh."), Validator.checkNumeric("ddgfh.").equals(""));
		assertEquals(Validator.checkNumeric("123"), Validator.checkNumeric("123"),"");
	}

	@Test
	public void testCyr() {
		assertFalse(Validator.checkCyrText("bodnar"),Validator.checkCyrText("bodnar").equals(""));
		assertFalse(Validator.checkCyrText("asdf32").equals(""));
		assertTrue(Validator.checkCyrText("фыв").equals(""));
		assertFalse(Validator.checkCyrText("ddgfh.").equals(""));
		assertFalse(Validator.checkCyrText("123").equals(""));
	}
	@Test
	public void testIsCyr() {
		assertFalse(Validator.isCyrillic("bodnar"));
		assertFalse(Validator.isCyrillic("asdf32"));
		assertTrue(Validator.isCyrillic("фыв"));
		assertFalse(Validator.isCyrillic("ddgfh."));
		assertFalse(Validator.isCyrillic("123"));
	}
}
