package com.atishsungum.app.thread.synchronization.pitfall;

import com.atishsungum.app.Process;
import com.atishsungum.app.thread.synchronization.BaseApiProcess;

public class PitfallProcessor extends BaseApiProcess implements Process {

    private int countApi1 = 0;
    private int countApi2 = 0;

    @Override
    public void execute() {
        if(multiThreading){
            callApisMultiThread();
        } else {
            callApisSingleThread();
        }
    }

    @Override
    public void onPostExecute() {
        System.out.println("Api 1: "+ countApi1 + ", Api 2: " + countApi2 + " with multithreading set to " + multiThreading);
        countApi1 = 0;
        countApi2 = 0;
    }

    /**
     * Simulate call to api1 which takes 10ms
     */
    @Override
    protected void callApi1(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("Failed to sleep thread");
        }

        countApi1++;
    }

    /**
     * Simulate call to api2 which takes 10ms
     */
    @Override
    protected void callApi2(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("Failed to sleep thread");
        }

        countApi2++;
    }

    /**
     * Calls 2 apis which are independent of each other in terms of data but dependent in terms of sequence; meaning
     * api1 needs to be called first then api2 needs to be called.
     * <br/>
     * Each call takes around 10ms, together they take around 20ms;
     */
    private void callApis(){
        callApi1();
        callApi2();
    }

    /**
     * Calls both apis 100 times consecutively using a single thread.
     * Result should be around 2 seconds
     */
    private void callApisSingleThread(){
        // resultant time should be 2 seconds
        for (int i = 0; i < 100; i++) {
            callApis();
        }
    }

    /**
     * Calls both apis consecutively for 100 times but 50 times in 2 threads to improve performance.
     * <br/>
     * Time should be reduced to half, from around 2 seconds, it becomes around 1 second.
     * <br/>
     * But given both threads is reading and writing on the same values, it tends to corrupt the value based on the
     * LOST UPDATE issue.
     */
    private void callApisMultiThread(){
        // basically speeding up callApisSingleThread by call callApis in 2 threads 50 times instead of 100 times
        // in one thread.
        Runnable runnable = () -> {
            for (int i = 0; i < 50; i++) {
                callApis();
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        try {
            // wait for process to finish
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Failed to wait for process completion");
        }
    }
}
