package com.atishsungum.app.thread.synchronization.poc;

import com.atishsungum.app.thread.synchronization.BaseApiProcess;

/**
 * A simple class simulating 2 APIs calls and keeps track of the total number of calls made.
 */
public class ApiCountAggregator extends BaseApiProcess {

    private int apiCallCount = 0;

    public void execute() {
        if(multiThreading){
            processWithMultipleThreads();
        } else {
            processWithSingleThread();
        }
    }

    private void processWithSingleThread() {
        callApi1();
        callApi2();
    }

    private void processWithMultipleThreads() {
        // execute api calls using multi threading
        Thread thread1 = new Thread(this::callApi1);
        Thread thread2 = new Thread(this::callApi2);

        thread1.start();
        thread2.start();

        try {
            // wait for threads to complete their work
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Failed to wait for thread completion");
        }
    }

    @Override
    public void onPostExecute() {
        System.out.println("Perform " + apiCallCount + " api calls" + " with multithreading set to " + multiThreading);

        // reset count after processing
        apiCallCount = 0;
    }

    /**
     * Simulate call to api1 100 times
     */
    @Override
    protected void callApi1(){
        // simulate 100 api calls each taking 10 milliseconds, in total around 1 second to do everything
        for (int i = 0; i <100; i++){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Failed to sleep thread");
            }

            incrementCount();
        }
    }

    /**
     * Simulate call to api2 100 times.
     */
    @Override
    protected void callApi2(){
        // simulate 100 api calls each taking 10 milliseconds, in total around 1 second to do everything
        for (int i = 0; i <100; i++){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Failed to sleep thread");
            }

            incrementCount();
        }
    }

    /**
     * Increment api call count
     * <br/>
     * When called by multiple threads, it might happen one of the threads overwrite the correct value in the variable.
     * <br/>
     * <pre>
     * ----- flow of time ---->
     * FLOW1: T1 reads variable as 2 ---------- updates variable to 3 --->
     * FLOW2: -- T2 reads variable as 2 ---------- updates variable to 3 --->
     * </pre>
     * <br/>
     * Flow 1 and 2 are running in parallel. T1 reads the value as 2 first and proceeds to update it to 3. T2 also reads
     * it as 2 a bit later and proceeds to update it to 3. However, T1 does the update first, then T2 also updates it to
     * 3; which is wrong, this the value should have been updated to 4.
     * <br/>
     * This issue is similar to the LOST UPDATE problem in DBMS, but now on threads level.
     */
    void incrementCount(){
        apiCallCount++;
    }
}
