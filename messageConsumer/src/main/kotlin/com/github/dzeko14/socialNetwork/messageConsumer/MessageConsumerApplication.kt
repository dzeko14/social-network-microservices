package com.github.dzeko14.socialNetwork.messageConsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MessageConsumerApplication

fun main(args: Array<String>) {
	runApplication<MessageConsumerApplication>(*args)
}
