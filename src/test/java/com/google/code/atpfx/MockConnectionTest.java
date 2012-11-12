/*
 * Copyright (c) 2011. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.google.code.atpfx;

import com.google.code.atpfx.base.MockConnection;
import com.google.code.atpfx.base.SpringTestBase;
import com.google.code.atpfx.handler.IDFHandler;
import org.junit.Test;

public class MockConnectionTest extends SpringTestBase {

    @Test
    public void testGenerate() {

        IConnection conn = new MockConnection();

        IDFHandler ih = new IDFHandler();
        
        conn.addListener(ih);

        conn.open();

        // wait 10 sec , kill conn
        try {
            Thread.sleep(1000 * 10);
        } catch(InterruptedException ex) {
            System.err.println(ex);
        }

        conn.close();

        
    }
}
