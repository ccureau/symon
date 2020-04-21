package com.loomcom.symon.cpus._65xx.common;

import com.loomcom.symon.Bus;
import com.loomcom.symon.cpus._65xx.interfaces.CpuStack;
import com.loomcom.symon.cpus.components.Register;
import com.loomcom.symon.exceptions.MemoryAccessException;

/**
 * GenericStack - a generic stack representation for the 65xx family
 * Implemented as a generic 8-bit register with operations to match
 *
 * The stack page cannot be changed on most 65xx CPUs.
 */
public class GenericStack implements CpuStack {
    /**
     * The byte in memory which defines the top of the stack page.
     * In the majority of cases, this will be set to 0x01.
     */
    public Register page;

    /**
     * The current pointer into the stack
     * Calculated as (page*256)+ptr
     */
    public Register ptr;

    public GenericStack() {
        this.ptr = new Register();
        this.page = new Register();
        reset();
    }

    public void reset() {
        this.page.setValue(0x01);
        this.ptr.setValue(0xFF);
    }

    /**
     * Calculate the physical memory address for the current pointer
     * @return an int representing the pointer in physical memory
     */
    private int toAddress() {
        return (page.getLowByte() << 8) + ptr.getLowByte();
    }

    /**
     * Push a Register onto the stack and decrement the stack pointer.
     * For a push when ptr = 0x00, the ptr wraps around to 0xFF.
     * @param register the Register that should be pushed onto the stack.
     * @param bus an instance of Bus where the stack data exists.
     */
    public void push(Register register, Bus bus) throws MemoryAccessException {
        if (register.getSize() == Register.BITS_16) {
            push(register.getHighByte(), bus);
        }
        push(register.getLowByte(), bus);
    }

    public void push(int value, Bus bus) throws MemoryAccessException {
        bus.write(toAddress(), value);
        ptr.decrement();
    }

    /**
     * Pull a Register from the stack and increment the stack pointer.
     * For a pull where ptr = 0xFF, the ptr wraps around to 0x00.
     * @param register the register that should be pushed onto the stack.
     * @param bus an instance of Bus where the stack data exists.
     */
    public void pull(Register register, Bus bus) throws MemoryAccessException {
        register.setLowByte(pull(bus));
        if (register.getSize() == Register.BITS_16) {
            register.setHighByte(pull(bus));
        }
    }

    public int pull(Bus bus) throws MemoryAccessException {
        int value = bus.read(toAddress(), true);
        ptr.increment();
        return value;
    }

    public int getValue(Bus bus) throws MemoryAccessException {
        return bus.read(toAddress(), true);
    }
}
