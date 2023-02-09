# Exception Test in TestNG

This is sample repository to understand Exception Test in TestNG through different examples.

## About TestNG
TestNG is an open-source automation testing framework for JAVA built on the capabilities of JUnit and NUnit. It helps to write testcases in a well-structured, easy-to-maintain way by means of annotations, attributes, and other features.

## Exception Test
An exception test is a test case that confirms a particular exception is raised whenever a certain code block is executed. Exception testing is an important part of software testing, as it helps to validate that the application behaves as expected when faced with exceptional scenarios. To write such a test, the TestNG testing framework is used.

Using the expectedExceptions attribute of the @Test annotation, we can write exception tests in TestNG. This attribute accepts a single exception class or a list of exception classes that the test function is expected to throw. The test will succeed if any of the predefined exceptions are raised. The test will fail if a different exception or no exception is thrown. Using the expectedExceptionsMessageRegExp attribute, we can also confirm the exception message.

### Exception Test Examples
#### For single exception
```@Test(expectedExceptions = ArithmeticException.class)
	public void test_single_exception()
	{
		int divideByZero = 1/0;
	}
```
  
#### For multiple exception
```@Test(expectedExceptions = {ArithmeticException.class, NullPointerException.class})
	public void test_multiple_exception()
	{ 
		int divideByZero = 1/0;
	}
```
  
#### For asserting exception message
```@Test(expectedExceptions = NumberFormatException.class, expectedExceptionsMessageRegExp = "Cannot parse null string")
	public void test_assert_exception_message()
	{ 
		String str = null;
		int val = Integer.parseInt(str);
	}
```
