package com.github.dzeko14.socialNetwork.messageConsumer.consumer

import com.github.dzeko14.socialNetwork.messageConsumer.model.*
import com.github.dzeko14.socialNetwork.messageConsumer.repository.CommentLogRepository
import com.github.dzeko14.socialNetwork.rabbitmq.COMMENT_QUEUE
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@EnableRabbit
@Component
class CommentMessageConsumer @Autowired constructor(
    private val commentLogReadable: CommentLogRepository
) {
    @RabbitListener(queues = [COMMENT_QUEUE])
    fun listenUserQueue(message: RabbitMQMessage<CommentImpl>) {
        commentLogReadable.save(CommentLog(
                message.title,
                message.body,
                message.date
        ))
    }
}