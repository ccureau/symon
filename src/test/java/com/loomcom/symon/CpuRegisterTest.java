package com.loomcom.symon;

import com.loomcom.symon.cpus.components.Register;
import junit.framework.*;

import static junit.framework.TestCase.assertEquals;

public class CpuRegisterTest extends TestCase {

    Register r;

    public CpuRegisterTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(CpuRegisterTest.class);
    }

    public void setUp() {
        this.r = new Register(Register.BITS_8);
    }

    public void tearDown() {
        this.r = null;
    }

    /**
     *
     */
    public void test8BitRegister() {
        r.setValue(0xabcd);
        assertEquals(0x00, r.getHighByte());
        assertEquals(0xcd, r.getLowByte());
        assertEquals(0xcd, r.getValue());

        // Switching to native mode should not affect values
        r.setSize(Register.BITS_16);
        assertEquals(0x00, r.getHighByte());
        assertEquals(0xcd, r.getLowByte());
        assertEquals(0xcd, r.getValue());
    }

    public void test16BitRegister() {
        r.setSize(Register.BITS_16);
        r.setValue(0xabcd);
        assertEquals(0xab, r.getHighByte());
        assertEquals(0xcd, r.getLowByte());
        assertEquals(0xcd, r.getValue());

        // Switching to emulation mode should return 8bit values
        r.setSize(Register.BITS_8);
        assertEquals(0x00, r.getHighByte());
        assertEquals(0xcd, r.getLowByte());
        assertEquals(0xcd, r.getValue());
    }

}
