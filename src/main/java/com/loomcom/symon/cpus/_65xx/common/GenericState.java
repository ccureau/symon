package com.loomcom.symon.cpus._65xx.common;

import com.loomcom.symon.Cpu;
import com.loomcom.symon.cpus.components.Flags;
import com.loomcom.symon.cpus.components.Register;
import com.loomcom.symon.cpus._65xx.interfaces.CpuState;
import com.loomcom.symon.util.Utils;

public class GenericState implements CpuState {

    /**
     * Constants
     */
    public enum Registers {
        A,
        X,
        Y,
        PC
    }

    // Accumulator or Direct Register
    private Register a;

    // X Register
    private Register x;

    // Y Register
    private Register y;

    // Program Counter
    private Register pc;

    // Status Flags
    private Flags flags;

    public GenericState() {
        this.flags = new Flags(8);
    }

    /**
     * Snapshot a copy of the CpuState.
     *
     * (This is a copy constructor rather than an implementation of <code>Cloneable</code>
     * based on Josh Bloch's recommendation)
     *
     * @param s The CpuState to copy.
     */
    public GenericState(GenericState s) {
        this.a = s.a;
        this.x = s.x;
        this.y = s.y;
        this.pc = s.pc;
        this.flags = s.flags;
    }

    /**
     * @return The value of the Process Status Register, as a byte.
     */
    public int getStatusFlags() {
        return flags.toInt();
    }

    /**
     * @return A string representing the current status register state.
     */
    public String getProcessorStatusString() {
        return "[" +
                    (flags.isSet(Flags.NEGATIVE) ? 'N' : '.') +
                    (flags.isSet(Flags.OVERFLOW) ? 'V' : '.') +
                    "-" +
                    (flags.isSet(Flags.BREAK) ? 'B' : '.') +
                    (flags.isSet(Flags.DECIMAL) ? 'D' : '.') +
                    (flags.isSet(Flags.IRQDISABLE) ? 'I' : '.') +
                    (flags.isSet(Flags.ZERO) ? 'Z' : '.') +
                    (flags.isSet(Flags.CARRY) ? 'C' : '.') +
                "]";
    }

    public Register getRegister(Registers r) {
        switch(r) {
            case A:
                return a;
            case X:
                return x;
            case Y:
                return y;
            case PC:
                return pc;
        }
        return null;
    }

    public void setRegister(Registers r, int value) {
        switch(r) {
            case A:
                a.setValue(value);
                break;
            case X:
                x.setValue(value);
                break;
            case Y:
                y.setValue(value);
                break;
            case PC:
                pc.setValue(value);
                break;
        }
    }

    public void incrementRegister(Registers r) {
        switch(r) {
            case A:
                a.increment();
                break;
            case X:
                x.increment();
                break;
            case Y:
                y.increment();
                break;
            case PC:
                pc.increment();
                break;
        }
    }

    public void decrementRegister(Registers r) {
        switch(r) {
            case A:
                a.decrement();
                break;
            case X:
                x.decrement();
                break;
            case Y:
                y.decrement();
                break;
            case PC:
                pc.decrement();
                break;
        }
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }
}
