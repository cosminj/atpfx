package com.google.code.atpfx.base;

import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Ancestor class for all Spring based tests.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/repository-config.xml"})
//@Transactional(propagation = Propagation.REQUIRED, timeout = 3600)
public abstract class SpringTestBase extends TestCase {

    static {
        //  set some test global properties -- if needed
    }

    protected final static Log log = LogFactory.getLog(SpringTestBase.class);


    // utility method
    protected void secondsToWait(int i) {
        try {
            Thread.sleep(1000 * i);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}