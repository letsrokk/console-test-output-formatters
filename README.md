# cucumber-jvm-console-formatter

Console Output Formatter for Cucumber JVM

# Formatters

- org.fxclub.qa.cucumber.ConsoleFormatter - prints test progress to console, compatible with tests executed in parallel using https://github.com/temyers/cucumber-jvm-parallel-plugin

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
2017-08-25 11:00:11 INFO  - #   1  [PASSED] - Тест allure Сломанный: Сложение одинарное Сломанный []
2017-08-25 11:00:11 INFO  - #   2  [PASSED] - Тест allure Сломанный: Сложение двух чисел Сломанный [[1, 4, 5]]
2017-08-25 11:00:11 INFO  - #   4  [PASSED] - Тест allure Сломанный: Сложение двух чисел Сломанный [[1, 4, 5]]
2017-08-25 11:00:11 INFO  - #   3  [PASSED] - Тест allure 2: Scenario []
2017-08-25 11:00:11 INFO  - #   5  [PASSED] - Тест allure Сломанный: Сложение двух чисел Сломанный [[1, 4, 5]]
2017-08-25 11:00:11 INFO  - #   7  [PASSED] - Тест allure 2: Scenario Structure [[1, 1, 2]]
2017-08-25 11:00:11 ERROR - #   6  [FAILED] - Тест allure Сломанный: Сложение двух чисел Сломанный [[1, 4, 5]]

Дано первое число 5
И второе число 10
Когда я их складываю
Тогда сумма равна 16

java.lang.AssertionError: expected:<16> but was:<15>
	at org.junit.Assert.fail(Assert.java:88)
	at org.junit.Assert.failNotEquals(Assert.java:834)
	at org.junit.Assert.assertEquals(Assert.java:645)
	at org.junit.Assert.assertEquals(Assert.java:631)
	at org.fclub.qa.cucumber.examples.steps.Steps.сумма_равна(Steps.java:50)
	at ✽.Тогда сумма равна 16(/Users/majer-dy/Documents/IDEA/cucumber-jvm-console-formatter/src/test/resources/features/test-allure-failed.feature:15)

2017-08-25 11:00:11 INFO  - #   8  [PASSED] - Тест allure 2: Scenario Structure [[1, 1, 2]]
2017-08-25 11:00:11 INFO  - #   9  [PASSED] - Тест allure Сломанный: Сложение одинарное Сломанный2 [[1, 4, 5]]
2017-08-25 11:00:11 INFO  - #  10  [PASSED] - Тест allure 2: Scenario Structure 2 [[3, 1, 4]]
2017-08-25 11:00:11 INFO  - #  12  [PASSED] - Тест allure 2: Scenario Structure 2 [[3, 1, 4]]
2017-08-25 11:00:11 ERROR - #  11 [SKIPPED] - Тест allure Сломанный: Пропущен [[1, 4, 5]]
2017-08-25 11:00:11 ERROR - #  14  [FAILED] - Тест allure Сломанный: Сломан [[1, 4, 5]]

Дано сломанный сценарий
Когда отображается отчет
То видно исключение

2017-08-25 11:00:11 INFO  - #  13  [PASSED] - Тест allure: Сложение одинарное []
2017-08-25 11:00:12 INFO  - #  16  [PASSED] - Тест allure: Сложение двух чисел [[6, 6, 12]]
2017-08-25 11:00:12 INFO  - #  17  [PASSED] - Тест allure: Сложение двух чисел [[6, 6, 12]]
2017-08-25 11:00:12 INFO  - #  18  [PASSED] - Тест allure: Сложение двух чисел еще раз [[12, 5, 17]]
2017-08-25 11:00:12 INFO  - #  19  [PASSED] - Тест allure: Сложение двух чисел еще раз [[12, 5, 17]]
2017-08-25 11:00:12 INFO  - #  15  [PASSED] - Text allure 3: Scenario []
2017-08-25 11:00:12 INFO  - #  20  [PASSED] - Text allure 3: Scenario Structure [[http://www.google.es, http://www.leda-mc.com, http://www.google.eshttp://www.leda-mc.com]]
2017-08-25 11:00:12 INFO  - #  21  [PASSED] - Text allure 3: Scenario Structure [[http://www.google.es, http://www.leda-mc.com, http://www.google.eshttp://www.leda-mc.com]]
2017-08-25 11:00:12 INFO  - #  22  [PASSED] - Test TextContext: Create and reuse Helper []
2017-08-25 11:00:12 INFO  - #  23  [PASSED] - Test TextContext: Create and reuse object []
[ERROR] Failures: 
[ERROR]   TestAllureFailed03Test>AbstractTestNGCucumberTests.feature:21 » Cucumber java....
[INFO] 
[ERROR] Tests run: 5, Failures: 1, Errors: 0, Skipped: 0
[INFO] 
[ERROR] There are test failures.

Please refer to /Users/majer-dy/Documents/IDEA/cucumber-jvm-console-formatter/target/surefire-reports for the individual test results.
Please refer to dump files (if any exist) [date]-jvmRun[N].dump, [date].dumpstream and [date]-jvmRun[N].dumpstream.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 5.986 s
[INFO] Finished at: 2017-08-25T11:00:12+02:00
[INFO] Final Memory: 20M/308M
[INFO] ------------------------------------------------------------------------
```