package com.atpfx.fxcm;

import static com.atpfx.message.PersistentMessageHandler.FXCM_LABEL;

import java.util.Properties;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.atpfx.infrastructure.SignalProviderRepository;
import com.atpfx.model.SignalProvider;
import com.fxcm.external.api.transport.FXCMLoginProperties;
import com.fxcm.external.api.transport.GatewayFactory;
import com.fxcm.external.api.transport.IGateway;
import com.fxcm.messaging.IUserSession;
import com.fxcm.messaging.util.IConnectionManager;

@Component
public class FxcmConnection {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${fxcm.username}")
    private String fxcmUsername;
    @Value("${fxcm.password}")
    private String fxcmPassword;
    @Value("${fxcm.user.session.pin}")
    private String sessionPin;

    @Resource
    private SignalProviderRepository signalProviderRepository;

    @Resource
    private ForexMessageListener forexMessageListener;

    private IGateway fxcmGateway;

    public IGateway connect() throws Exception {
        if (fxcmGateway != null && fxcmGateway.isConnected()) {
            return fxcmGateway;
        }

        SignalProvider signalProvider = signalProviderRepository.getByLabel(FXCM_LABEL);

        String fxcmUrl = signalProvider.getServerUrl();

        fxcmGateway = GatewayFactory.createGateway();
        fxcmGateway.registerGenericMessageListener(forexMessageListener);
        FXCMLoginProperties properties = new FXCMLoginProperties(fxcmUsername, fxcmPassword, "Demo", fxcmUrl);
        Properties props = new Properties();
        props.put(IUserSession.PIN, sessionPin);

        props.put(IConnectionManager.PROXY_SERVER, "localhost");
        props.put(IConnectionManager.PROXY_PORT, "3128");

        properties.setProperties(props);

        fxcmGateway.login(properties);

        // must call this "requestTradingSessionStatus" to start getting messages
        String status = fxcmGateway.requestTradingSessionStatus();

        logger.info("Connection opened with login: {}", status);

        return fxcmGateway;
    }
}