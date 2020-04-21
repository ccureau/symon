package com.loomcom.symon.cpus.components;

import com.loomcom.symon.cpus._65xx.interfaces.CpuState;

public class Register {

    // Constants
    public static final int BITS_8 = 1;
    public static final int BITS_16 = 2;

    // The value of this register. Defaults to 0
    int values[];

    // Size of this register. Defaults to BITS_8
    int size;

    // Can the register be read from and written to?
    // Both default to true
    boolean readable;
    boolean writable;
    
    // Some registers are mapped to physical memory
    boolean memoryMapped;
    int memoryLocation;

    public Register(int size) {
        this.values = new int[2];
        values[0] = 0;
        values[1] = 0;
        this.size = size;
        this.readable = true;
        this.writable = true;
    }
    
    // No constructor arguments defaults to 8-bit size
    public Register() {
    	this(BITS_8);
    }

    public int getSize() {
        return this.size;
    }

    public int getValue() {
        if (readable) {
            return (values[0] | (values[1] << 8));
        } else {
            return 0;
        }
    }

    public int getHighByte() {
        if (readable) {
            return values[1];
        } else {
            return 0;
        }
    }

    public int getLowByte() {
        if (readable) {
            return values[0];
        } else {
            return 0;
        }
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setValue(int value) {
        if (writable) {
        	// switch fall-through is intentional
            switch (size) {
                case BITS_16:
                    values[1] = value >> 8;
                default:
                    values[0] = value & 0xFF;
            }
        }
    }

    public void setHighByte(int value) {
        if (writable) {
            values[1] = value;
        }
    }

    public void setLowByte(int value) {
        if (writable) {
            values[0] = value;
        }
    }
    
    public void increment() {
    	boolean carry = false;
    	
    	values[0]++;
    	if (values[0] > 0xFF) {
    		values[0] = 0;
    		carry = true;
    	}
    	if ((size == BITS_16) && carry) {
    		values[1]++;
    		if (values[1] > 0xFF) {
    			values[1] = 0;
    		}
    	}
    }
    
    public void decrement() {
    	boolean carry = false;
    	
    	values[0]--;
    	if (values[0] < 0) {
    		values[0] = 0xFF;
    		carry = true;
    	}
    	if ((size == BITS_16) && carry) {
    		values[1]--;
    		if (values[1] < 0) {
    			values[1] = 0xFF;
    		}
    	}
    }
}
