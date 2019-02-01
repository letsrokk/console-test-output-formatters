package com.github.letsrokk.testng;

import org.testng.ITestResult;

/*default*/ class Functions {

    final private static int max_deep = 10;

    /*default*/ static String getTestParametersString(ITestResult tr){
        StringBuilder params = new StringBuilder();
        if(tr.getParameters().length == 0){
            params.append("none");
        } else {
            for(Object param : tr.getParameters()) {
                params.append("[").append(param == null ? "null" : param.toString()).append("],");
            }
        }
        return params.toString().replaceAll("],$", "]");
    }

    /*default*/ static String extractMessageError(Throwable t){
        return extractThrowable(t).getMessage();
    }

    private static Throwable extractThrowable(Throwable t){
        int deep = 0;
        while(t.getCause() != null && deep < max_deep){
            deep++;
            t = t.getCause();
        }
        return t;
    }

}
