package com.loomcom.symon.cpus._65xx.cmos;

import com.loomcom.symon.Bus;
import com.loomcom.symon.cpus._65xx.common.GenericStack;
import com.loomcom.symon.cpus._65xx.common.GenericState;
import com.loomcom.symon.cpus._65xx.interfaces.Cpu;
import com.loomcom.symon.cpus._65xx.interfaces.CpuState;
import com.loomcom.symon.cpus._65xx.interfaces.CpuStack;
import com.loomcom.symon.cpus.components.Register;
import com.loomcom.symon.exceptions.MemoryAccessException;
import com.loomcom.symon.util.Utils;

public class _65c02 implements Cpu {


    // Representation of a stack
    private CpuStack stack;

    // Representation of the CPU state
    private CpuState state;

    // The bus where this CPU is attached
    private Bus bus;

    /**
     * Last Loaded Instruction Register
     */
    private int ir;

    /**
     * Peek-Ahead to next IR
     */
    public int nextIr;
    public int[] args = new int[2];
    public int[] nextArgs = new int[2];
    public int instSize;
    public boolean opTrap;
    public boolean irqAsserted;
    public boolean nmiAsserted;
    public int lastPc;

    public long stepCounter = 0L;


    public _65c02() {
        this.stack = new GenericStack();
        this.state = new GenericState();
//        this.ir = s.ir;
//        this.nextIr = s.nextIr;
//        this.lastPc = s.lastPc;
//        this.args[0] = s.args[0];
//        this.args[1] = s.args[1];
//        this.nextArgs[0] = s.nextArgs[0];
//        this.nextArgs[1] = s.nextArgs[1];
//        this.instSize = s.instSize;
//        this.opTrap = s.opTrap;
//        this.irqAsserted = s.irqAsserted;
//        this.stepCounter = s.stepCounter;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Bus getBus() {
        return bus;
    }

    public void reset() throws MemoryAccessException {
        stack.reset();
    }

    public CpuStack getStack() {
        return stack;
    }

    public CpuState getState(){
        return state;
    }

    public String getInstructionByteStatus() {
        switch (com.loomcom.symon.Cpu.instructionSizes[ir]) {
            case 0:
            case 1:
                return Utils.wordToHex(lastPc) + "  " +
                        Utils.byteToHex(ir) + "      ";
            case 2:
                return Utils.wordToHex(lastPc) + "  " +
                        Utils.byteToHex(ir) + " " +
                        Utils.byteToHex(args[0]) + "   ";
            case 3:
                return Utils.wordToHex(lastPc) + "  " +
                        Utils.byteToHex(ir) + " " +
                        Utils.byteToHex(args[0]) + " " +
                        Utils.byteToHex(args[1]);
            default:
                return null;
        }
    }
    /**
     * Returns a string formatted for the trace log.
     *
     * @return a string formatted for the trace log.
     */
    public String toTraceEvent() throws MemoryAccessException {
        String opcode = com.loomcom.symon.Cpu.disassembleOp(ir, args);
        return state.getInstructionByteStatus() + "  " +
                String.format("%-14s", opcode) +
                "A:" + Utils.byteToHex(state.getRegister(GenericState.Registers.A).getValue()) + " " +
                "X:" + Utils.byteToHex(state.getRegister(GenericState.Registers.X).getValue()) + " " +
                "Y:" + Utils.byteToHex(state.getRegister(GenericState.Registers.Y).getValue()) + " " +
                "F:" + Utils.byteToHex(state.getStatusFlags()) + " " +
                "S:" + Utils.byteToHex(stack.getValue(bus)) + " " +
                state.getProcessorStatusString() + "\n";
    }
}
