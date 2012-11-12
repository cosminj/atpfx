package com.google.code.atpfx;

import com.fxcm.external.api.transport.FXCMLoginProperties;
import com.fxcm.external.api.transport.GatewayFactory;
import com.fxcm.external.api.transport.IGateway;
import com.fxcm.external.api.transport.listeners.IGenericMessageListener;
import com.fxcm.external.api.transport.listeners.IStatusMessageListener;
import com.fxcm.fix.NotDefinedException;
import com.fxcm.fix.pretrade.MarketDataSnapshot;
import com.fxcm.messaging.ISessionStatus;
import com.fxcm.messaging.ITransportable;
import com.fxcm.messaging.IUserSession;
import com.google.code.atpfx.data.Pair;
import com.google.code.atpfx.data.Pairs;
import com.google.code.atpfx.data.Tick;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

public class FXCMConnection implements IConnection {

    // accountLoginDetails holds username, password, account type
    private AccountLoginDetails accountLoginDetails;
    // connectionURL is specific for FXCM connection, used in open method
    // TODO: bring from property file, keep synchronized with DB
    private String connectionURL = "http://www.fxcorporate.com/Hosts.jsp";
    // signalProviderId=1 is specific for FXCM connection, accompanies all
    // FXCM accounts found in properties files, and must exist in DB in
    // table SignalProviders. Here is hard coded to reduce dependency of
    // DB. However, any new connection type must be synchronized with DB.
    // A call "FXCMConnection = SignalProviderTypes(FXCMConnection)" might
    // solve this variable without introducing a dependency between
    // connection and DB.
    private int signalProviderId = 1;
    // userSessionPIN is specific for FXCM connection, used in open method
    // TODO: bring from property file, keep synchronized with DB
    private String userSessionPIN = "111111";
    // the interface between streaming server and current program
    private IGateway fxcmGateway;
    // this is were data is received
    private ArrayList<ITransportable> genericMessagesReceived = new ArrayList<ITransportable>();
    private ArrayList<ISessionStatus> statusMessagesReceived = new ArrayList<ISessionStatus>();

    private IGenericMessageListener genericMessageListener = new IGenericMessageListener() {
        public void messageArrived(ITransportable message) {
            genericMessagesReceived.add(message);
        }
    };
    private IStatusMessageListener statusMessageListener = new IStatusMessageListener() {
        public void messageArrived(ISessionStatus status) {
            // Note: status messages could be null
            if (null != status) {
                statusMessagesReceived.add(status);
            }
        }
    };


    private List<IGenericMessageListener> listeners = new LinkedList<IGenericMessageListener>();

    // reference to the original standard error when routine starts
    private static PrintStream originalStandardError;

    public FXCMConnection() {
        this(new AccountLoginDetails().getUserName(), new AccountLoginDetails()
                .getPassword(), new AccountLoginDetails().getAccountType());
        listeners.add(genericMessageListener);
    }

    public FXCMConnection(String userName, String password, String accountType) {

        accountLoginDetails = new AccountLoginDetails(userName, password);

        // save the original reference of the standard error
        // because we may need to redirect it for silent error catching
        FXCMConnection.originalStandardError = System.err;
    }

    @Override
    public void addListener(IGenericMessageListener l) {
        listeners.add(l);
    }

    @Override
    public void open() {

        if (null == this.accountLoginDetails) return;

        fxcmGateway = GatewayFactory.createGateway();
        for (IGenericMessageListener list : listeners) {
            fxcmGateway.registerGenericMessageListener(list);
        }

        fxcmGateway.registerStatusMessageListener(statusMessageListener);

        FXCMLoginProperties properties = new FXCMLoginProperties(
                accountLoginDetails.getUserName(), accountLoginDetails
                        .getPassword(), accountLoginDetails.getAccountType(),
                connectionURL, null);
        Properties props = new Properties();
        props.put(IUserSession.PIN, this.userSessionPIN);

        /**
         * PROXY #####################
         *
        props.put(IConnectionManager.PROXY_SERVER, "158.169.9.13");
        props.put(IConnectionManager.PROXY_PORT, 8012);
        props.put(IConnectionManager.PROXY_UID, "");
        props.put(IConnectionManager.PROXY_PWD, "");
         */

    
        properties.setProperties(props);

        ByteArrayOutputStream tempBAOS = new ByteArrayOutputStream();
        PrintStream tempPrintStream = new PrintStream(tempBAOS);
        System.setErr(tempPrintStream);

        // if you want to redirect the errors to a file, use the below
        // the file is created in the root of the project,
        // same location like pom.xml
        //
        // try {
        // PrintStream tempPrintStream = new PrintStream(
        // new BufferedOutputStream(new FileOutputStream("test.txt")));
        // } catch (IOException e) {
        // System.err.println("ERROR: Could not redirect errors to file.");
        // System.err.println("       If there are connection issues using username ");
        // System.err.println("       "+ userName + " and password " +
        // password);
        // System.err.println(" 	   they will throw an error.");
        // System.err.println(e.getLocalizedMessage());
        // }

        try {
            fxcmGateway.login(properties);
            // must call this "requestTradingSessionStatus" to start getting
            // messages
            fxcmGateway.requestTradingSessionStatus();
            System.out.println("Connection opened with login "
                    + this.accountLoginDetails.getUserName());
        } catch (Exception e) {
            e.printStackTrace();// this goes to System.err
            System.out.println("ERROR: " + e.getLocalizedMessage());
            System.out.println("       User: "
                    + accountLoginDetails.getUserName() + " Password: "
                    + accountLoginDetails.getPassword());
            System.out
                    .println("       Incorrect credentials or password expired.");
        }

        System.setErr(FXCMConnection.originalStandardError);

    }

    @Override
    public boolean isOpened() {
        return fxcmGateway.isConnected();
    }

    @Override
    public void close() {

        if (fxcmGateway.isConnected()) {
            fxcmGateway.logout();
        }

        System.out.println("Generic messages received: "
                + genericMessagesReceived.size());
        System.out.println("Status messages received: "
                + statusMessagesReceived.size());
    }

    public ArrayList<ITransportable> getGenericMessagesReceived() {
        return genericMessagesReceived;
    }

    public String getUserName() {
        return accountLoginDetails.getUserName();
    }    

    
    


}