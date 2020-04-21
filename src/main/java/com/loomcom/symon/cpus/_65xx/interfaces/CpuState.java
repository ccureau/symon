package com.loomcom.symon.cpus._65xx.interfaces;

import com.loomcom.symon.cpus._65xx.common.GenericState;
import com.loomcom.symon.cpus.components.Flags;
import com.loomcom.symon.cpus.components.Register;

public interface CpuState {

    /**
     * @return The value of the Process Status Register, as a byte.
     */
    public int getStatusFlags();
    
    /**
     * @return A String representing the current state of the CPU
     * based on the instruction executed
     */
    String getInstructionByteStatus();
    
    /**
     * @return A string representing the current status register state.
     */
    String getProcessorStatusString();

    public Register getRegister(GenericState.Registers r);

    public void setRegister(GenericState.Registers r, int value);

    public void incrementRegister(GenericState.Registers r);

    public void decrementRegister(GenericState.Registers r);

    public Flags getFlags();

    public void setFlags(Flags flags);
}
