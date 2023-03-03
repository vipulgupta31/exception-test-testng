# Exception Test in TestNG

This is sample repository to understand Exception Test in TestNG through different examples. Exception test basically helps to validate the execptionn raised in case an unexpected event occurs in execution.

## About TestNG
TestNG is an open-source automation testing framework for JAVA built on the capabilities of JUnit and NUnit. It helps to write testcases in a well-structured, easy-to-maintain way by means of annotations, attributes, and other features.

## Exception Test
An exception test is a test case that confirms a particular exception is raised whenever a certain code block is executed. Exception testing is an important part of software testing, as it helps to validate that the application behaves as expected when faced with exceptional scenarios. To write such a test, the TestNG testing framework is used.

Using the expectedExceptions attribute of the @Test annotation, we can write exception tests in TestNG. This attribute accepts a single exception class or a list of exception classes that the test function is expected to throw. The test will succeed if any of the predefined exceptions are raised. The test will fail if a different exception or no exception is thrown. Using the expectedExceptionsMessageRegExp attribute, we can also confirm the exception message.

### Exception Test Examples
#### For single exception
```
@Test(expectedExceptions = NoSuchElementException.class)
public void test_single_exception()
{
	//code which throws NoSuchElementException
}
```
  
#### For multiple exception
```
@Test(expectedExceptions = {NoSuchElementException.class, NullPointerException.class})
public void test_multiple_exception()
{ 
	//code which can throw either of 
	//NoSuchElementException or NullPointerException
}
```
  
#### For asserting exception message
```
@Test(expectedExceptions = NumberFormatException.class, expectedExceptionsMessageRegExp = "Cannot parse null string")
public void test_assert_exception_message()
{ 
	//code to throw exception with similar exception message
}
```

## About Project
It is created using Selenium with Java, TestNG and Maven for Web-based automation to handle SSL certificates.

This is the list of tools, being used in this framework:
1. Apache Maven
2. Java 8
3. Selenium Cloud Grid - [LambdaTest](http://www.lambdatest.com?fp_ref=vipul51) Platform
4. TestNG Framework

## Steps for Local Execution
1. Import this project in Eclipse/IntelliJ as “Existing Maven Project”
2. Go to the test file and Run test case for your desired case.
3. You can see the logs coming up on Console as your execution progresses.
4. Since we are using RemoteWebDriver and executing on Cloud Grid platform, [LambdaTest](http://www.lambdatest.com?fp_ref=vipul51), you can login to same and view detailed logs on dashboard.
