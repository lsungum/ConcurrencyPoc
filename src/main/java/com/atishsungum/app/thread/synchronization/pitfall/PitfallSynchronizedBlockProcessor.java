package com.atishsungum.app.thread.synchronization.pitfall;

/**
 * Instead of using synchronized on the methods itself, use another object to support the synchronization process,
 * an extrinsic lock. The extrinsic lock can be on any other object, the synchronization uses the object's in-built
 * locking mechanism to allow for processing.
 */
public class PitfallSynchronizedBlockProcessor extends PitfallProcessor {

    private final Object api1Lock = new Object();
    private final Object api2Lock = new Object();

    @Override
    protected void callApi1() {
        synchronized (api1Lock){
            super.callApi1();
        }
    }

    @Override
    protected void callApi2() {
        synchronized (api2Lock){
            super.callApi2();
        }
    }
}
