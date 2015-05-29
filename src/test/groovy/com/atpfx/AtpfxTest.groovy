package com.atpfx

import com.atpfx.infrastructure.SignalProviderRepository
import com.atpfx.model.SignalProvider
import com.atpfx.rest.CommandServlet

import javax.annotation.Resource

import static com.atpfx.message.MarketDataSnapshotHandler.FXCM_LABEL

class AtpfxTest extends BaseIntegrationTest {

    @Resource
    private CommandServlet commandServlet
    @Resource
    private SignalProviderRepository signalProviderRepository

    def setup() {
        signalProviderRepository.save(new SignalProvider(FXCM_LABEL, 'http://www.fxcorporate.com/Hosts.jsp'))
    }

    def "context loads correctly for Atpfx app"() {
        expect: 1
    }

    def "logs in to fxcm"() {
        expect:
        commandServlet.connect()
    }
}