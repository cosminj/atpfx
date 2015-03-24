package com.atpfx.fxcm

import com.atpfx.message.MessageHandler
import com.fxcm.entity.ICode
import com.fxcm.messaging.ITransportable
import spock.lang.Specification

class ForexMessageListenerTest extends Specification {

    ForexMessageListener forexMessageListener
    MessageHandler marketHandler
    MessageHandler tradingHandler

    def setup() {
        marketHandler = Mock(MessageHandler)
        tradingHandler = Mock(MessageHandler)
        forexMessageListener = new ForexMessageListener(
                handlers: [marketHandler, tradingHandler]
        )
    }

    def "maps handlers by code on post construct"() {
        when:
        forexMessageListener.postConstruct()
        then:
        1 * marketHandler.fxcmType() >> 'a'
        1 * tradingHandler.fxcmType() >> 'b'
        forexMessageListener.handlerMap == [ 'a' : marketHandler, 'b' : tradingHandler ]
    }

    def "calls corresponding message handler by code" () {
        given:
        forexMessageListener.handlerMap = [ 'a' : marketHandler ]
        def mesg = Mock(ITransportable)
        def icode = Mock(ICode)
        when:
        forexMessageListener.messageArrived(mesg)
        then:
        1 * mesg.getType() >> icode
        1 * icode.getCode() >> 'a'
        1 * marketHandler.handle(mesg)
    }
}