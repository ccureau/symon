package com.loomcom.symon.cpus._65xx.interfaces;

public interface CpuFlags {
	/**
	 * Set a flag (1)
	 * @param f Flag to change
	 */
    public void set(int f);
    
    /**
     * Clear a flag (0)
     * @param f Flag to change
     */
    public void clear(int f);
        
    /**
     * Determine if a flag is set (true) or cleared (false)
     * @param f Flag to check
     * @return
     */
    public boolean isSet(int f);
    
    /**
     * Return the Flags as an integer
     * @return
     */
    public int toInt();
    
    /**
     * Set the Flags based on this integer
     * @param f Integer representation of the Flag state
     */
    public void setFrom(int value);
    
    /**
     * Return the Flags as a formatted String
     * @return
     */
    public String toString();
}
