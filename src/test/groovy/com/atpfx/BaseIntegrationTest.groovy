package com.atpfx

import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@ActiveProfiles("test")
@IntegrationTest("server.port:0")
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Atpfx)
abstract class BaseIntegrationTest extends Specification {

}
