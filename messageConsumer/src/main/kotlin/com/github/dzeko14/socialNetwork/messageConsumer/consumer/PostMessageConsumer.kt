package com.github.dzeko14.socialNetwork.messageConsumer.consumer

import com.github.dzeko14.socialNetwork.messageConsumer.model.PostImpl
import com.github.dzeko14.socialNetwork.messageConsumer.model.PostLog
import com.github.dzeko14.socialNetwork.messageConsumer.model.RabbitMQMessage
import com.github.dzeko14.socialNetwork.messageConsumer.repository.PostLogRepository
import com.github.dzeko14.socialNetwork.rabbitmq.POST_QUEUE
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@EnableRabbit
@Component
class PostMessageConsumer @Autowired constructor(
        private val postLogRepository: PostLogRepository
) {
    @RabbitListener(queues = [POST_QUEUE])
    fun listenUserQueue(message: RabbitMQMessage<PostImpl>) {
        postLogRepository.save(PostLog(
                message.title,
                message.body,
                message.date
        ))
    }
}