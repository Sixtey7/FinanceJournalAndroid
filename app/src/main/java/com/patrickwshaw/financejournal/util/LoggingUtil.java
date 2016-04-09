package com.patrickwshaw.financejournal.util;

import android.util.Log;

import java.io.Serializable;
import java.util.Stack;

/**
 * Created by Patrick on 4/3/2016.
 */
public class LoggingUtil implements Serializable {
    private String TAG;
    private String className;
    private Stack<String> methodNames;

    //No one should ever call the default constructor
    private LoggingUtil() {
        //don't use this!
    }

    public LoggingUtil(final Class<?> clazz) {
        methodNames = new Stack<>();
        this.TAG = clazz.getSimpleName();
        this.className = clazz.getSimpleName();
    }
    public LoggingUtil(final String TAG, final String className) {
        methodNames = new Stack<>();
        this.TAG = TAG;
        this.className = className;
    }

    public void logEnter(final String methodName) {
        methodNames.push(methodName);
        Log.d(TAG, className + " - " + methodName + " - Enter");
    }

    public void logExit() {
        String methodName = methodNames.pop();
        Log.d(TAG, className + " - " + methodName + " - Exit");
    }

    public void logInnerClassEnter(String innerClassName, String methodName)
    {
        Log.d(TAG, className + " - " + innerClassName + " - " + methodName + " - Enter");
    }

    public void logInnerClassExit(String innerClassName, String methodName)
    {
        Log.d(TAG, className + " - " + innerClassName + " - " + methodName + " - Exit");
    }

    public void debug(String message)
    {
        Log.d(TAG, message);
    }

    public void info(String message)
    {
        Log.i(TAG, message);
    }

    public void warn(String message)
    {
        Log.w(TAG, message);
    }

    public void error(String message)
    {
        Log.e(TAG, message);
    }

    public void d(String message)
    {
        debug(message);
    }

    public void i(String message)
    {
        info(message);
    }

    public void w(String message)
    {
        warn(message);
    }

    public void e(String message)
    {
        error(message);
    }
}
