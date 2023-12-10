package com.atishsungum.app.thread.synchronization.poc;

import com.atishsungum.app.ProcessExecutor;

public class ApiAggregatorStarter {

    public static void main(String[] args){
        System.out.println("\n----- Using default count aggregator -------");
        ProcessExecutor.executeInSequence(new ApiCountAggregator());

        System.out.println("\n--- Using synchronized count aggregator ----");
        ProcessExecutor.executeInSequence(new SynchronizedApiCountAggregator());
    }
}

