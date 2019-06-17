package com.github.dzeko14.socialNetwork.messageConsumer.consumer

import com.github.dzeko14.socialNetwork.interactors.repository.UserMessageRepository
import com.github.dzeko14.socialNetwork.messageConsumer.model.*
import com.github.dzeko14.socialNetwork.messageConsumer.repository.UserMessageLogRepository
import com.github.dzeko14.socialNetwork.rabbitmq.MESSAGE_QUEUE
import com.github.dzeko14.socialNetwork.rabbitmq.USER_QUEUE
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@EnableRabbit
@Component
class UserMessageConsumer (
        private val repo: UserMessageLogRepository
) {
    @RabbitListener(queues = [MESSAGE_QUEUE])
    fun listenUserQueue(message: RabbitMQMessage<UserMessageImpl>) {
        val autheoId = message.body?.author?.id ?: 0
        repo.save(MessageLog(
                message.title,
                message.body?.id ?: 0,
                message.body?.content ?: "",
                autheoId,
                message.date
        ))
    }
}