package com.loomcom.symon.cpus._65xx.wdc.w65c816;

import com.loomcom.symon.Bus;
import com.loomcom.symon.cpus._65xx.GenericStack;
import com.loomcom.symon.cpus.components.Register;
import com.loomcom.symon.exceptions.MemoryAccessException;

/**
 * Implement a 16-bit capable stack compatible with the 65816 and 65265 CPUs
 */
public class Stack extends GenericStack {

    public void push(Register register, Bus bus) throws MemoryAccessException {
        if (register.size == Register.BITS_16) {
            bus.write(ptr.getValue(), register.getHighByte());
            ptr.decrement();
        }
        super.push(register, bus);
    }

    public void pull(Register register, Bus bus) throws MemoryAccessException {
        super.pull(register, bus);
        if (register.size == Register.BITS_16) {
            register.setHighByte(bus.read(ptr.getValue(), true));
            ptr.increment();
        }
    }
}
