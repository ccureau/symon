package com.loomcom.symon.cpus.addressing;

import com.loomcom.symon.Bus;
import com.loomcom.symon.cpus._65xx.interfaces.CpuState;

/**
 * Lay out the generic addressing modes common to all 65xx CPUs
 * Override with specific details for each supported CPU
 * @author ccureau
 *
 */
public class Operations {
	public void stackPush(int value, CpuState state, Bus bus) {
		bus.write(state);
	}
}
