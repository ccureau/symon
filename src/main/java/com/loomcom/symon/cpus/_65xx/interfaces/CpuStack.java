package com.loomcom.symon.cpus._65xx.interfaces;

import com.loomcom.symon.Bus;
import com.loomcom.symon.cpus.components.Register;
import com.loomcom.symon.exceptions.MemoryAccessException;

public interface CpuStack {
    /**
     * Initialize stack to power-up defaults
     */
    public void reset();

    /**
     * Push a value onto the stack, decrementing the pointer
     * @param register Regsiter to push onto the stack
     * @param bus Location where the stack exists in memory
     */
    public void push(Register register, Bus bus) throws MemoryAccessException;

    public void push(int value, Bus bus) throws MemoryAccessException;

    /**
     * Pull a value from the stack, incrementing the pointer
     * @param register
     * @param bus
     */
    public void pull(Register register, Bus bus) throws MemoryAccessException;

    public int pull(Bus bus) throws MemoryAccessException;

    public int getValue(Bus bus) throws MemoryAccessException;
}
