package org.fxclub.qa.cucumber2;

import cucumber.api.Result;
import cucumber.api.event.*;
import cucumber.api.formatter.Formatter;
import cucumber.api.formatter.NiceAppendable;
import cucumber.runtime.formatter.TestSourcesModel;
import dnl.utils.text.table.TextTable;
import gherkin.ast.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleOutputPlugin implements Formatter {

    private Logger logger = LogManager.getLogger(ConsoleOutputPlugin.class);

    private static AtomicInteger testCaseStartedCounter = new AtomicInteger(0);
    private static AtomicInteger testCaseFinishedCounter = new AtomicInteger(0);

    private NiceAppendable out;
    private boolean useNiceAppendable = true;

    public ConsoleOutputPlugin(Appendable out) {
        this.out = new NiceAppendable(out);
    }

    private void info(String message){
        if(useNiceAppendable)
            consoleOutput(message);
        else
            logger.info(message);
    }

    private void debug(String message){
        if(useNiceAppendable)
            consoleOutput(message);
        else
            logger.debug(message);
    }

    private void error(String message){
        if(useNiceAppendable)
            consoleOutput(message);
        else
            logger.error(message);
    }

    private void consoleOutput(String message){
        out.println(message);
    }

    private final TestSourcesModel testSources = new TestSourcesModel();

    private final EventHandler<TestSourceRead> featureStartedHandler = this::handleFeatureStartedHandler;
    private final EventHandler<TestCaseStarted> caseStartedHandler = this::handleTestCaseStarted;
    private final EventHandler<TestCaseFinished> caseFinishedHandler = this::handleTestCaseFinished;

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestSourceRead.class, featureStartedHandler);
        //eventPublisher.registerHandlerFor(TestCaseStarted.class, caseStartedHandler);
        eventPublisher.registerHandlerFor(TestCaseFinished.class, caseFinishedHandler);
    }

    private void handleFeatureStartedHandler(final TestSourceRead event) {
        testSources.addTestSourceReadEvent(event.uri, event);
    }

    private void handleTestCaseStarted(final TestCaseStarted event) {
        int currentTestCaseCounter = testCaseStartedCounter.incrementAndGet();
        String featureName = testSources.getFeature(event.testCase.getUri()).getName();
        String scenarioName = event.testCase.getName();
        ScenarioDefinition scenarioDefinition =
                testSources.getScenarioDefinition(event.testCase.getUri(), event.testCase.getLine());
        String params = getExamplesAsParameters(scenarioDefinition, event.testCase.getLine());

        String message = String.format(
                "#%4d %9s - %s: %s %s",
                currentTestCaseCounter,
                "[STARTED]",
                featureName,
                scenarioName,
                params
        );
        debug(message);
    }

    private void handleTestCaseFinished(final TestCaseFinished event) {
        int currentTestCaseCounter = testCaseFinishedCounter.incrementAndGet();

        String featureName = testSources.getFeature(event.testCase.getUri()).getName();
        String scenarioName = event.testCase.getName();
        ScenarioDefinition scenarioDefinition =
                testSources.getScenarioDefinition(event.testCase.getUri(), event.testCase.getLine());
        String params = getExamplesAsParameters(scenarioDefinition, event.testCase.getLine());

        if(event.result.getStatus() == Result.Type.PASSED){
            String message = String.format(
                    "#%4d %9s - %s: %s %s",
                    currentTestCaseCounter,
                    "["+event.result.getStatus().lowerCaseName().toUpperCase()+"]",
                    featureName,
                    scenarioName,
                    params
            );
            info(message);
        } else {
            String lineSeparator = System.getProperty("line.separator");
            String scenarioSteps = getScenarioAsString(scenarioDefinition);

            String status = event.result.getStatus() == Result.Type.UNDEFINED
                    ? Result.Type.SKIPPED.lowerCaseName().toUpperCase()
                    : event.result.getStatus().lowerCaseName().toUpperCase();

            String message = String.format(
                    "#%4d %9s - %s: %s %s",
                    currentTestCaseCounter,
                    "["+status+"]",
                    featureName,
                    scenarioName,
                    params
            );

            if(!scenarioSteps.equals("")){
                message = String.format(
                        message + lineSeparator+"%s",
                        lineSeparator + scenarioSteps
                );
            }

            if(event.result.getErrorMessage() != null && !event.result.getErrorMessage().equals("")){
                message = String.format(
                        message + lineSeparator+"%s",
                        event.result.getErrorMessage()
                );
            }

            error(message);
        }
    }

    private String getScenarioAsString(ScenarioDefinition scenarioDefinition){
        StringBuilder scenarioBuilder = new StringBuilder();

        for(Step step : scenarioDefinition.getSteps()){
            String line = step.getKeyword() + step.getText();
            scenarioBuilder.append(line);
            scenarioBuilder.append(System.getProperty("line.separator"));

            if(step.getArgument() instanceof DataTable){
                DataTable dataTable = (DataTable) step.getArgument();

                String[] columnNames = IntStream.range(0, dataTable.getRows().get(0).getCells().size())
                        .mapToObj(index -> "" + (index+1))
                        .toArray(String[]::new);

                String[][] dataTableArray = dataTable.getRows().stream()
                        .map(this::getRowAsStringArray)
                        .toArray(String[][]::new);

                TextTable dataTableText = new TextTable(columnNames, dataTableArray);

                ByteArrayOutputStream os = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(os);
                dataTableText.printTable(ps,4);
                scenarioBuilder.append(os.toString());
                scenarioBuilder.append(System.getProperty("line.separator"));
            }
        }
        return scenarioBuilder.toString();
    }

    private String[] getRowAsStringArray(TableRow row){
        return row.getCells().stream()
                .map(TableCell::getValue)
                .toArray(String[]::new);
    }

    private String getExamplesAsParameters(final ScenarioDefinition scenarioDefinition, final int testCaseLine) {
        if(scenarioDefinition instanceof ScenarioOutline){
            final Examples examples = ((ScenarioOutline) scenarioDefinition).getExamples().get(0);
            final TableRow row = examples.getTableBody().stream()
                    .filter(example -> example.getLocation().getLine() == testCaseLine)
                    .findFirst().get();

            List<String> parameters =
                    row.getCells().stream()
                            .map(cell -> "[" + cell.getValue() + "]")
                            .collect(Collectors.toList());

            return parameters.toString();
        } else {
            return "";
        }
    }

}
