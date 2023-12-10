package com.atishsungum.app.thread.synchronization;

import com.atishsungum.app.Process;

public abstract class BaseApiProcess implements Process {

    protected boolean multiThreading;

    protected abstract void callApi1();

    protected abstract void callApi2();

    public void enableMultiThreading(boolean enabled){
        multiThreading = enabled;
    }
}
