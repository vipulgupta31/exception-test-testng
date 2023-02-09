package test;


import java.io.IOException;

import org.testng.annotations.Test;

public class ExceptionTest {
	
	@Test
	public void test_without_exception()
	{
		int divideByZero = 1/0;
	}
	
	@Test(expectedExceptions = ArithmeticException.class)
	public void test_single_exception()
	{
		int divideByZero = 1/0;
	}
	
	@Test(expectedExceptions = {ArithmeticException.class})
	public void test_single_exception_with_curly_brackets()
	{
		int divideByZero = 1/0;
	}
	
	@Test(expectedExceptions = {NullPointerException.class})
	public void test_exception_failed()
	{
		int divideByZero = 1/0;
	}
	
	//raised exception is a subset of the list
	@Test(expectedExceptions = {ArithmeticException.class, NullPointerException.class})
	public void test_multiple_exception()
	{ 
		int divideByZero = 1/0;
	}
	
	//raised exception is not present in the list
	@Test(expectedExceptions = {IOException.class, ArithmeticException.class})
	public void test_multiple_exception_failed()
	{ 
		String str = null;
			
		int val = Integer.parseInt(str);
	}
	
	//assert exception message pass
	@Test(expectedExceptions = NumberFormatException.class, expectedExceptionsMessageRegExp = "Cannot parse null string")
	public void test_assert_exception_message()
	{ 
		String str = null;
				
		int val = Integer.parseInt(str);
	}
	
	//assert exception message failed
	@Test(expectedExceptions = NumberFormatException.class, expectedExceptionsMessageRegExp = "Cannot parse null")
	public void test_assert_exception_message_failed()
	{ 
		String str = null;
				
		int val = Integer.parseInt(str);
	}
	
	//assert exception message with regular expression
	@Test(expectedExceptions = NumberFormatException.class, expectedExceptionsMessageRegExp = ".* parse null .*")
	public void test_assert_exception_message_regex()
	{ 
		String str = null;
					
		int val = Integer.parseInt(str);
	}
}
