package com.google.code.atpfx.data;

import com.google.code.atpfx.AccountLoginDetails;
import com.google.code.atpfx.FXCMConnection;
import com.google.code.atpfx.base.SpringTestBase;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Cosmin
 * Date: 21-Jul-2010
 * Time: 15:42:01
 * To change this template use File | Settings | File Templates.
 */
public class DataTests extends SpringTestBase {

    @Test
    public void testConnectToFXCM() {

        AccountLoginDetails accountLoginDetails = new AccountLoginDetails();
        boolean hasConnected = false;
        FXCMConnection myFXCMConnection = new FXCMConnection(
                accountLoginDetails.getUserName(),
                accountLoginDetails.getPassword(),
                accountLoginDetails.getAccountType());

        assertFalse("The connection has not yet been initiated", hasConnected);
        myFXCMConnection.open();
        hasConnected = myFXCMConnection.isOpened();

        for (int counter = 0; counter < 10; counter++) {
            // wait a second
            secondsToWait(1);

            while (myFXCMConnection.getGenericMessagesReceived().size() > 0) {
                // as long as there are messages in the received queue
                // process them, look for ticks, and save ticks in DB
                //Tick t = myFXCMConnection.getNextTick();
                // TODO: attention! t might be null here!
//                if (null != t) {
//                    // saving the tick
//                    System.out.println("t = " + t);
//                }
            }
        }

        myFXCMConnection.close();
        assertTrue("The connection could not be established", hasConnected);
    }
}
