package com.github.dzeko14.socialNetwork.messageConsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
class MessageConsumerApplication

fun main(args: Array<String>) {
	runApplication<MessageConsumerApplication>(*args)
}
