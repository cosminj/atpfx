package com.atpfx.message

import com.atpfx.infrastructure.SignalProviderRepository
import com.atpfx.infrastructure.TickRepository
import com.atpfx.model.Pair
import com.atpfx.model.SignalProvider
import com.atpfx.model.Tick
import com.fxcm.fix.Instrument
import com.fxcm.fix.entity.MarketDataSnapshot
import spock.lang.Specification

import static com.atpfx.message.MarketDataSnapshotHandler.FXCM_LABEL

class MarketDataSnapshotHandlerTest extends Specification {

    private TickRepository tickRepository
    private SignalProviderRepository signalProviderRepository
    private MarketDataSnapshotHandler handler

    def setup() {
        given:
        tickRepository = Mock(TickRepository)
        signalProviderRepository = Mock(SignalProviderRepository)
        handler = new MarketDataSnapshotHandler(
                tickRepository: tickRepository,
                signalProviderRepository: signalProviderRepository
        )
    }

    def "caches the fxcm provider object on post construct" () {
        given:
        def provider = new SignalProvider(FXCM_LABEL, 'url')
        when:
        handler.postConstruct()
        then:
        1 * signalProviderRepository.getByLabel(FXCM_LABEL) >> provider
        handler.fxcmProvider == provider
    }

    def "should save an incoming tick to database"() {
        Tick tick
        when:
        handler.handle(new MarketDataSnapshot(instrument: new Instrument("$Pair.AUD_CAD")))
        then:
        1 * tickRepository.save(_ as Tick) >> { tick = it[0]}
        tick.pair == Pair.AUD_CAD
    }
}