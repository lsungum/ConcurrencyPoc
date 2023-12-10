package com.atishsungum.app.thread.synchronization.poc;

public class SynchronizedApiCountAggregator extends ApiCountAggregator {

    /**
     * Execute the same method but this time with an intrinsic lock on the current instance of the class. This resolves
     * the issue where the count is not consistent with the number of api call made.
     * <br/>
     * Every object has an in-built lock mechanism (intrinsic lock) and it is not attached to the state of the object.
     * Adding <code>synchronized</code> on a method in the class, means it will be using the in-built lock mechanism
     * of that class's instance.
     * <br/>
     * Acquiring the lock associated with an object does not prevent other threads from accessing that object, it only
     * prevents other threads from acquiring same lock.
     * <br/>
     * <b>Pitfall?</b> When adding synchronized on multiple methods in the same class, the synchronized methods will use
     * the same lock available on the class instance, which might hinder performance, especially if the methods are not
     * related in any way
     */
    @Override
    synchronized void incrementCount() {
        super.incrementCount();
    }
}
