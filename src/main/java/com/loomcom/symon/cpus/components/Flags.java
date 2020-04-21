package com.loomcom.symon.cpus.components;

import com.loomcom.symon.cpus._65xx.interfaces.CpuFlags;

import java.util.BitSet;

public class Flags implements CpuFlags {

	public static int NEGATIVE = 8;
	public static int OVERFLOW = 7;
	public static int UNUSED = 6;
	public static int BREAK = 5;
	public static int DECIMAL = 4;
	public static int IRQDISABLE = 3;
	public static int ZERO = 2;
	public static int CARRY = 1;
	
	private BitSet flags;
	
	public Flags(int count) {
		this.flags = new BitSet(count);
		flags.set(UNUSED);
	}

	@Override
	public void set(int f) {
		flags.set(f);
	}

	@Override
	public void clear(int f) {
		flags.clear(f);
	}

	@Override
	public boolean isSet(int f) {
		return flags.get(f);
	}

	@Override
	public int toInt() {
		return (int) flags.toLongArray()[0];
	}

	@Override
	public void setFrom(int value) {
		flags = BitSet.valueOf(new long[] {value});
	}
}
