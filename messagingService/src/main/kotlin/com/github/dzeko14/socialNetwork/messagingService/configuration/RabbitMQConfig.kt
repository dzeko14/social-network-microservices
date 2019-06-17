package com.github.dzeko14.socialNetwork.messagingService.configuration

import com.github.dzeko14.socialNetwork.rabbitmq.*
import org.springframework.context.annotation.Bean
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.context.annotation.Configuration
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter


@Configuration
class RabbitMQConfig {
    @Bean
    fun chatQueue(): Queue {
        return Queue(CHAT_QUEUE, false)
    }

    @Bean
    fun messageQueue(): Queue {
        return Queue(MESSAGE_QUEUE, false)
    }


    @Bean
    fun producerMessageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun rabbitTemplate(cf: ConnectionFactory): RabbitTemplate {
        val rt = RabbitTemplate(cf)
        rt.messageConverter = producerMessageConverter()
        return rt
    }
}