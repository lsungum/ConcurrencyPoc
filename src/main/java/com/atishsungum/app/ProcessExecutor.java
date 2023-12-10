package com.atishsungum.app;

public class ProcessExecutor {

    private ProcessExecutor(){
        // no need to instantiate executor
    }

    /**
     * Execute the process in the sequence defined below;
     * 1. Execute process with single thread
     * 2. Execute process with multiple threads
     *
     * @param process to be executed
     */
    public static void executeInSequence(Process process){
        execute(process);
        process.enableMultiThreading(true);
        execute(process);
    }

    public static void execute(Process process){
        long startTime = System.currentTimeMillis();

        process.execute();

        long endTime = System.currentTimeMillis();

        process.onPostExecute();

        System.out.println("Took "+ ((endTime - startTime) / 1000.0) + " seconds\n");
    }
}
