package com.github.dzeko14.socialNetwork.messageConsumer.consumer

import com.github.dzeko14.socialNetwork.messageConsumer.model.RabbitMQMessage
import com.github.dzeko14.socialNetwork.messageConsumer.model.UserImpl
import com.github.dzeko14.socialNetwork.messageConsumer.model.UserLog
import com.github.dzeko14.socialNetwork.messageConsumer.repository.UserLogRepository
import com.github.dzeko14.socialNetwork.rabbitmq.USER_QUEUE
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@EnableRabbit
@Component
class UserConsumer @Autowired constructor(
        private val userLogRepository: UserLogRepository
) {

    @RabbitListener(queues = [USER_QUEUE])
    fun listenUserQueue(message: RabbitMQMessage<UserImpl>) {
        userLogRepository.save(UserLog(
                message.title,
                message.body,
                message.date
        ))
    }
}