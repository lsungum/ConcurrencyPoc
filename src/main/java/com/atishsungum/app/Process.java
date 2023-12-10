package com.atishsungum.app;

public interface Process {
    /**
     * Executes the process
     */
    void execute();

    default void onPostExecute(){
        System.out.println("Process execution completed");
    };

    /**
     * Enable multi threading on execution of the process
     * @param enabled if true, it allows for multi threading
     */
    void enableMultiThreading(boolean enabled);
}
