package com.atpfx.message

import com.atpfx.infrastructure.SignalProviderRepository
import com.atpfx.infrastructure.TickRepository
import com.atpfx.model.Pair
import com.atpfx.model.SignalProvider
import com.atpfx.model.Tick
import com.fxcm.fix.Instrument
import com.fxcm.fix.entity.MarketDataSnapshot
import spock.lang.Specification

class PersistentMessageHandlerTest extends Specification {

    private TickRepository tickRepository
    private SignalProviderRepository signalProviderRepository

    def setup() {
        given:
        tickRepository = Mock(TickRepository)
        signalProviderRepository = Mock(SignalProviderRepository)
//        handler = new PersistentMessageHandler(
//                tickRepository: tickRepository,
//                signalProviderRepository: signalProviderRepository
//        )
    }
//
//    def "should save an incoming tick to database"() {
//        Tick tick
//        when:
//        handler.handleMessage(new MarketDataSnapshot(
//                instrument: new Instrument("$Pair.AUD_CAD")
//        ))
//        then:
//        1 * signalProviderRepository.getByLabel(FXCM_LABEL) >> new SignalProvider(FXCM_LABEL, 'url')
//        1 * tickRepository.save(_ as Tick) >> { tick = it[0]}
//        tick.pair == Pair.AUD_CAD
//    }
}