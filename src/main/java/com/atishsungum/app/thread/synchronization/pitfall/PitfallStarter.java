package com.atishsungum.app.thread.synchronization.pitfall;

import com.atishsungum.app.ProcessExecutor;

public class PitfallStarter {

    public static void main(String[] args){
        System.out.println("\n--- Using PitfallProcessor ---");
        PitfallProcessor pitfallProcessor = new PitfallProcessor();
        ProcessExecutor.executeInSequence(pitfallProcessor);

        System.out.println("\n--- Using PitfallSynchronizedProcessor ---");
        pitfallProcessor = new PitfallSynchronizedProcessor();
        ProcessExecutor.executeInSequence(pitfallProcessor);

        System.out.println("\n--- Using PitfallSynchronizedBlockProcessor ---");
        pitfallProcessor = new PitfallSynchronizedBlockProcessor();
        ProcessExecutor.executeInSequence(pitfallProcessor);
    }
}
