# cucumber-jvm-console-plugins [ ![Download](https://api.bintray.com/packages/letsrokk/github/cucumber-jvm-console-plugins/images/download.svg) ](https://bintray.com/letsrokk/github/cucumber-jvm-console-plugins/_latestVersion)

Print out Scenario execution results in console. 
Created to print results for Scenarios which are executed in parallel (e.g. using [cucumber-jvm-parallel-plugin](https://github.com/temyers/cucumber-jvm-parallel-plugin)) 

_**Important note**_:
Supported only parallel execution in threads, without forking VMs. With VM forks, test counter may not work properl.

e.g. parallel execution with `maven-surefire-plugin`: `forkCount` parameter can only be set to `0` or `1`.

# Cucumber JVM 2.0

Plugin name `org.fxclub.qa.cucumber2.ConsoleOutputPlugin`

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
#   1  [PASSED] - Тест allure 2: Scenario Structure [[1], [1], [2]]
#   2  [PASSED] - Тест allure: Сложение одинарное 
#   3  [PASSED] - Тест allure 2: Scenario 
#   4  [PASSED] - Тест allure 2: Scenario Structure [[2], [2], [4]]
#   5  [PASSED] - Тест allure 2: Scenario Structure 2 [[4], [1], [5]]
#   6  [PASSED] - Тест allure 2: Scenario Structure 2 [[3], [1], [4]]
#   7  [PASSED] - Тест allure: Сложение двух чисел [[6], [6], [12]]
#   8  [PASSED] - Тест allure: Сложение двух чисел еще раз [[12], [5], [17]]
#   9  [PASSED] - Тест allure: Сложение двух чисел [[5], [10], [15]]
#  10  [PASSED] - Тест allure: Сложение двух чисел еще раз [[22], [11], [33]]
#  11  [FAILED] - Test with Data Tables: Сложение одинарное 

Дано 3 числа:
    _________
    | 1| 2| 3|
    |========|
    | 5| 5| 8|
    | 1| 7| 5|

Когда я их складываю
Тогда сумма равна 11

java.lang.AssertionError: expected [10] but found [11]
	at org.testng.Assert.fail(Assert.java:94)
	at org.testng.Assert.failNotEquals(Assert.java:513)
	at org.testng.Assert.assertEqualsImpl(Assert.java:135)
	at org.testng.Assert.assertEquals(Assert.java:116)
	at org.testng.Assert.assertEquals(Assert.java:389)
	at org.testng.Assert.assertEquals(Assert.java:399)
	at org.fxclub.qa.cucumber2.examples.steps.Steps.сумма_равна(Steps.java:54)
	at ✽.сумма равна 11(/Users/majer-dy/Documents/IDEA/cucumber-jvm-console-formatter/cucumber2-jvm-console-plugin/src/test/resources/features/test-allure-datatables.feature:19)

#  12  [PASSED] - Test with Data Tables: Сложение одинарное 
#  13  [PASSED] - Тест allure Сломанный: Сложение одинарное Сломанный 
#  14  [PASSED] - Тест allure Сломанный: Сложение двух чисел Сломанный [[1], [4], [5]]
#  15  [PASSED] - Тест allure Сломанный: Сложение двух чисел Сломанный [[1], [2], [3]]
#  16  [PASSED] - Тест allure Сломанный: Сложение двух чисел Сломанный [[3], [7], [10]]
#  17 [SKIPPED] - Тест allure Сломанный: Пропущен 

Дано не реализованный сценарий
Когда он выполнен
Тогда его шаги отмечены как "Skipped"

#  18  [FAILED] - Тест allure Сломанный: Сломан 

Дано сломанный сценарий
Когда отображается отчет
То видно исключение

java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
	at org.fxclub.qa.cucumber2.examples.steps.Steps.сломанный_сценарий(Steps.java:62)
	at ✽.сломанный сценарий(/Users/majer-dy/Documents/IDEA/cucumber-jvm-console-formatter/cucumber2-jvm-console-plugin/src/test/resources/features/test-allure-failed.feature:40)

#  19  [PASSED] - Тест allure Сломанный: Сложение одинарное Сломанный2 
#  20  [FAILED] - Тест allure Сломанный: Сложение двух чисел Сломанный [[5], [10], [16]]

Дано первое число <первое>
И второе число <второе>
Когда я их складываю
Тогда сумма равна <сумма>

java.lang.AssertionError: expected [15] but found [16]
	at org.testng.Assert.fail(Assert.java:94)
	at org.testng.Assert.failNotEquals(Assert.java:513)
	at org.testng.Assert.assertEqualsImpl(Assert.java:135)
	at org.testng.Assert.assertEquals(Assert.java:116)
	at org.testng.Assert.assertEquals(Assert.java:389)
	at org.testng.Assert.assertEquals(Assert.java:399)
	at org.fxclub.qa.cucumber2.examples.steps.Steps.сумма_равна(Steps.java:54)
	at ✽.сумма равна 16(/Users/majer-dy/Documents/IDEA/cucumber-jvm-console-formatter/cucumber2-jvm-console-plugin/src/test/resources/features/test-allure-failed.feature:17)

#  21  [PASSED] - Text allure 3: Scenario 
#  22  [PASSED] - Test TextContext: Create and reuse Helper 
#  23  [PASSED] - Test TextContext: Create and reuse object 
#  24  [PASSED] - Text allure 3: Scenario Structure [[http://www.google.es], [http://www.leda-mc.com], [http://www.google.eshttp://www.leda-mc.com]]
#  25  [PASSED] - Text allure 3: Scenario Structure [[http://www.leda-mc.com], [http://www.google.es], [http://www.leda-mc.comhttp://www.google.es]]
[ERROR] Failures: 
[ERROR]   TestAllureDatatables10Test.runScenario expected [10] but found [11]
[ERROR]   TestAllureFailed15Test.runScenario expected [15] but found [16]
[ERROR]   TestAllureFailed17Test>AbstractTestNGCucumberTests.runScenario:22 » Cucumber T...
[ERROR]   TestAllureFailed18Test.runScenario » ClassCast java.lang.Integer cannot be cas...
[INFO] 
[ERROR] Tests run: 25, Failures: 4, Errors: 0, Skipped: 0
[INFO] 
[ERROR] There are test failures.

Please refer to /Users/majer-dy/Documents/IDEA/cucumber-jvm-console-formatter/cucumber2-jvm-console-plugin/target/surefire-reports for the individual test results.
Please refer to dump files (if any exist) [date]-jvmRun[N].dump, [date].dumpstream and [date]-jvmRun[N].dumpstream.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 9.143 s
[INFO] Finished at: 2017-10-23T11:25:31+02:00
[INFO] Final Memory: 21M/302M
[INFO] ------------------------------------------------------------------------
``` 

# Cucumber JVM 1.x

Formatter name: `org.fxclub.qa.cucumber.ConsoleFormatter`

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
	at Steps.сумма_равна(Steps.java:50)
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
