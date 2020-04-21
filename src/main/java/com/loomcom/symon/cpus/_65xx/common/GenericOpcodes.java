package com.loomcom.symon.cpus._65xx.common;

import com.loomcom.symon.Bus;
import com.loomcom.symon.cpus._65xx.interfaces.Cpu;
import com.loomcom.symon.cpus._65xx.interfaces.CpuFlags;
import com.loomcom.symon.cpus._65xx.interfaces.CpuStack;
import com.loomcom.symon.cpus._65xx.interfaces.CpuState;
import com.loomcom.symon.cpus.components.Flags;
import com.loomcom.symon.exceptions.MemoryAccessException;

/**
 * Provide a common set of opcodes which can be overridden as needed
 * by each CPU's behaviors.
 *
 * This opcode set targets the CMOS 6502 chipset, as it is considered
 * 'correct' behavior with all of the NMOS bugs fixed.
 */
public class GenericOpcodes {
    /**
     * 0x00: BRK
     * Actions:
     *  - PC += 1
     *  - push PC hi, then PC low
     *  - push Flags
     *  - clear decimal flag
     *  - set interrupt disable flag
     *  - set PC to vector at 0xFFFE
     */
    public void brk(Cpu cpu, Bus bus) throws MemoryAccessException {
        CpuState state = cpu.getState();
        CpuStack stack = cpu.getStack();
        CpuFlags flags = state.getFlags();

        state.getRegister(GenericState.Registers.PC).increment();
        stack.push(state.getRegister(GenericState.Registers.PC), bus);
        stack.push(state.getStatusFlags(), bus);
        flags.clear(Flags.DECIMAL);
        flags.set(Flags.IRQDISABLE);
        int newPC = (bus.read(cpu.IRQ_VECTOR_H, true) << 8) + bus.read(cpu.IRQ_VECTOR_L, true);
        state.setRegister(GenericState.Registers.PC, newPC);
    }

    /**
     * 0x01: ORA ZP,X
     */
}
