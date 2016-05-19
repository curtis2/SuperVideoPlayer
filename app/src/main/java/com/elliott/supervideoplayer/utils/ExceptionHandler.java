package com.elliott.supervideoplayer.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */
public class ExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {
    private final String LINE_SEPARATOR = "\n";
    public static final String LOG_TAG = ExceptionHandler.class.getSimpleName();

    @SuppressWarnings("deprecation")
    public void uncaughtException(Thread thread, Throwable exception) {
        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));

        StringBuilder errorReport = new StringBuilder();
        errorReport.append(stackTrace.toString());

        LogUtils.e(LogUtils.LOG_TAG, errorReport.toString());

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }
}