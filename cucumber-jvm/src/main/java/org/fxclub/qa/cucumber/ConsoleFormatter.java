package org.fxclub.qa.cucumber;

import cucumber.runtime.CucumberException;
import cucumber.runtime.StepDefinitionMatch;
import dnl.utils.text.table.TextTable;
import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * ConsoleFormatter for Cucumber JVM: print finished Scenarios to console
 */
public class ConsoleFormatter implements Reporter, Formatter {

    private Logger logger = LoggerFactory.getLogger(ConsoleFormatter.class);

    private Feature currentFeature;
    private Examples currentExamples;
    private Scenario currentScenario;
    private List<Step> currentSteps = new ArrayList<>();
    private Result currentResult;
    private String currentStatus;

    private final String FAILED = Result.FAILED;
    private final String SKIPPED = Result.SKIPPED.getStatus();
    private final String UNDEFINED = Result.UNDEFINED.getStatus();
    private final String PASSED = Result.PASSED;

    @Override
    public void syntaxError(String s, String s1, List<String> list, String s2, Integer integer) {

    }

    @Override
    public void uri(String s) {

    }

    @Override
    public void feature(Feature feature) {
        this.currentFeature = feature;
    }

    @Override
    public void scenarioOutline(ScenarioOutline scenarioOutline) {
        //this.currentScenarioOutline = scenarioOutline;
    }

    @Override
    public void examples(Examples examples) {
        this.currentExamples = examples;
    }

    @Override
    public void startOfScenarioLifeCycle(Scenario scenario) {
        this.currentStatus = PASSED;
        TestContext.setContext(new TestContext(scenario.getName()));
    }

    @Override
    public void background(Background background) {

    }

    @Override
    public void scenario(Scenario scenario) {
        this.currentScenario = scenario;
    }

    @Override
    public void step(Step step) {

    }

    @Override
    public void endOfScenarioLifeCycle(Scenario scenario) {
        int index = ConsoleFormatterTestCounter.getFinishCounter();
        String featureName = currentFeature.getName();
        String scenarioName = currentScenario.getName();
        String params = getParametersString(currentScenario, this.currentExamples);

        if(currentStatus.equals(Result.PASSED)){
            String message = String.format("#%4d %9s - %s: %s [%s]",index,"["+currentStatus.toUpperCase()+"]",featureName,scenarioName,params);
            logger.info(message);
        } else {
            String lineSeparator = System.getProperty("line.separator");
            String scenarioSteps = getScenarioAsString(currentSteps);

            String message = String.format(
                    "#%4d %9s - %s: %s [%s]",
                    index,"["+currentStatus.toUpperCase()+"]",featureName, scenarioName, params
            );

            if(StringUtils.isNotEmpty(scenarioSteps)){
                message = String.format(
                        message + lineSeparator+"%s",
                        lineSeparator + scenarioSteps
                );
            }

            if(StringUtils.isNotEmpty(currentResult.getErrorMessage())){
                message = String.format(
                        message + lineSeparator+"%s",
                        currentResult.getErrorMessage()
                );
            }

            logger.error(message);
        }

        currentSteps.clear();
    }

    private String getScenarioAsString(List<Step> steps){
        StringBuilder scenarioBuilder = new StringBuilder();
        for(Step step : steps){
            String line = step.getKeyword() + step.getName();
            scenarioBuilder.append(line);
            scenarioBuilder.append(System.getProperty("line.separator"));

            List<DataTableRow> dataTable = step.getRows();
            if(dataTable != null) {
                Object[][] data = dataTableToArray(dataTable);
                TextTable dataTableText = new TextTable(getRowsNames(data[0].length), data);

                ByteArrayOutputStream os = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(os);
                dataTableText.printTable(ps,4);
                scenarioBuilder.append(os.toString());
                scenarioBuilder.append(System.getProperty("line.separator"));
            }
        }
        return scenarioBuilder.toString();
    }

    private String[] getRowsNames(int columnsCount){
        List<String> columns = new ArrayList<>();
        for(int i = 1; i <= columnsCount; i++){
            columns.add("Value " + i);
        }
        return columns.toArray(new String[]{});
    }

    private String[][] dataTableToArray(List<DataTableRow> rows){
        int rowsCount = rows.size();
        int columnCount = rows.get(0).getCells().size();

        String[][] table = new String[rowsCount][columnCount];

        for(int i = 0; i < rowsCount; i++){
            for(int j = 0; j < columnCount; j++) {
                table[i][j] = rows.get(i).getCells().get(j);
            }
        }

        return table;
    }

    private String getParametersString(Scenario scenario, Examples examples){
        if(examples == null || examples.getRows().size() == 0)
            return "";
        else
            return examples.getRows().stream()
                    .filter(exmpl -> exmpl.getLine() == scenario.getLine())
                    .findFirst()
                    .get()
                    .getCells()
                    .toString();
    }

    @Override
    public void done() {

    }

    @Override
    public void close() {

    }

    @Override
    public void eof() {

    }

    @Override
    public void before(Match match, Result result) {
        System.out.println();
    }

    @Override
    public void result(Result result) {
        this.currentResult = result;
        if(FAILED.equals(result.getStatus())){
            this.currentStatus = FAILED;
        } else if (SKIPPED.equals(result.getStatus()) || UNDEFINED.equals(result.getStatus())){
            if(PASSED.equals(this.currentStatus)){
                currentStatus = SKIPPED;
            }
        }
    }

    @Override
    public void after(Match match, Result result) {

    }

    @Override
    public void match(Match match) {
        if(match instanceof StepDefinitionMatch) {
            currentSteps.add(extractStep((StepDefinitionMatch) match));
        }
    }

    private Step extractStep(StepDefinitionMatch match) {
        try {
            Field step = match.getClass().getDeclaredField("step");
            step.setAccessible(true);
            return (Step) step.get(match);
        } catch (ReflectiveOperationException e) {
            //shouldn't ever happen
            logger.error(e.getMessage(), e);
            throw new CucumberException(e);
        }
    }

    @Override
    public void embedding(String s, byte[] bytes) {

    }

    @Override
    public void write(String s) {

    }
}
