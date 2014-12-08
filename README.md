[Allure]: http://allure.qatools.ru
[TestNG]: http://testng.org/doc/index.html

#Allure TestNG Retry Listener

Implementation of [TestNG] ITestListener to allow "Retry Functionality" for combination of [Allure] Framework and [TestNG] Framework.
See JavaDocs here (TBD).

##Features:

 - If test has been retried, its result status would be changed from "Failed"("Broken") to "Pending", with all saved steps and attachments
 - If test failed and it hasn't "retry ability", its result status wouldn't be changed
 - User can use SimpleRetryAnalyzer which works with [TestNG] dataProviders and gets MAX_RETRY_COUNT from system properties (see the example below)
 - User can implement IAllureRetryAnalyzer, or extend from AbstractAllureRetryAnalyzer to set the rule for retry (to write custom analyzer)
 - There is RetryUtils.getResultHash() method which returns special hashcode from ITestResult instance (to write custom analyzer)

##How-to:

Simple usage example:

```java
@Listeners(RetryListener.class)
public class ExampleTest {
    @Test(retryAnalyzer = SimpleRetryAnalyzer.class)
    public void test() {
        // test logic
    }
}
```

To apply RetryAnalyzer for whole suite:

```java
@BeforeSuite(alwaysRun = true)
public void setUpSuite(ITestContext context) {
    for (ITestNGMethod method : context.getAllTestMethods()) {
        if (method.getRetryAnalyzer() == null) {
            method.setRetryAnalyzer(new SimpleRetryAnalyzer());
        }
    }
}
```

Set MAX_RETRY_COUNT for SimpleRetryAnalyzer example:

```java
System.setProperty(SimpleRetryAnalyzer.RETRY_COUNT_PROPERTY_KEY, "2");
    // "alluretestng.retrylistener.retryCount" property key
```

Custom RetryAnalyzer example:

```java
public static class TestRetryAnalyzer extends AbstractAllureRetryAnalyzer {
    private static final int MAX_RETRY_COUNT = 1;
    private int retryCount;

    @Override
    public boolean retry(ITestResult result, boolean getRetryAbilityOnly) {
        if (retryCount < MAX_RETRY_COUNT) {
            if (!getRetryAbilityOnly)
                retryCount++;
            return true;
        }
        return false;
    }
}
```

## Contact
Mail: [paulakimenko@gmail.com](mailto:paulakimenko@gmail.com)
