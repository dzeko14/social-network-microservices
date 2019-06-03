package com.github.dzeko14.socialNetwork.messagingService

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

//@EnableEurekaClient
@SpringBootApplication
class MessagingServiceApplication

fun main(args: Array<String>) {
	runApplication<MessagingServiceApplication>(*args)
}
