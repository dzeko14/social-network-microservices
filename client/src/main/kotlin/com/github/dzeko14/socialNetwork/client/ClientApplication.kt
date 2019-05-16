package com.github.dzeko14.socialNetwork.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
class ClientApplication

fun main(args: Array<String>) {
	runApplication<ClientApplication>(*args)
}
