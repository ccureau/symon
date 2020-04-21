package com.loomcom.symon.cpus._65xx.interfaces;

import com.loomcom.symon.exceptions.MemoryAccessException;

public interface Cpu {

    // NMI vector
    public static final int NMI_VECTOR_L = 0xfffa;
    public static final int NMI_VECTOR_H = 0xfffb;
    // Reset vector
    public static final int RST_VECTOR_L = 0xfffc;
    public static final int RST_VECTOR_H = 0xfffd;
    // IRQ vector
    public static final int IRQ_VECTOR_L = 0xfffe;
    public static final int IRQ_VECTOR_H = 0xffff;

    public static final long DEFAULT_CLOCK_PERIOD_IN_NS = 1000;

    /* Simulated clock speed (default is 1MHz) */
    public long clockPeriodInNs = DEFAULT_CLOCK_PERIOD_IN_NS;

    public CpuStack getStack();
    public CpuState getState();
    public void reset() throws MemoryAccessException;
    /**
     * Returns a string formatted for the trace log.
     *
     * @return a string formatted for the trace log.
     */
    String toTraceEvent() throws MemoryAccessException;
}
