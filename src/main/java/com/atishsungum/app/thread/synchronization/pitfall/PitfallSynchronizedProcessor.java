package com.atishsungum.app.thread.synchronization.pitfall;

/**
 * Methods of the class annotated with synchronized to help deal with lost update issue.
 * <br/>
 * BUT the total time taken will still be the same as the single thread processing; around 2 seconds, since both methods
 * are using the same lock which is on the class's instance. So, if a thread acquires the lock to process callApi1,
 * another thread which has already processed callApi1 and needs to proceed with callApi2, simply cannot do so, it needs
 * to wait for the first thread to release the lock on the class before doing anything.
 */
public class PitfallSynchronizedProcessor extends PitfallProcessor {
    @Override
    synchronized protected void callApi1() {
        super.callApi1();
    }

    @Override
    synchronized protected void callApi2() {
        super.callApi2();
    }
}
