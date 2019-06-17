package com.github.dzeko14.socialNetwork.messageConsumer.consumer

import com.github.dzeko14.socialNetwork.messageConsumer.model.ChatLog
import com.github.dzeko14.socialNetwork.messageConsumer.model.CommentImpl
import com.github.dzeko14.socialNetwork.messageConsumer.model.CommentLog
import com.github.dzeko14.socialNetwork.messageConsumer.model.RabbitMQMessage
import com.github.dzeko14.socialNetwork.messageConsumer.repository.ChatLogRepo
import com.github.dzeko14.socialNetwork.rabbitmq.CHAT_QUEUE
import com.github.dzeko14.socialNetwork.rabbitmq.COMMENT_QUEUE
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@EnableRabbit
@Component
class ChatMessageConsumer(
        private val repo: ChatLogRepo
) {
    @RabbitListener(queues = [CHAT_QUEUE])
    fun listenUserQueue(message: RabbitMQMessage<String>) {
        repo.save(ChatLog(
                message.title,
                message.body,
                message.date
        ))
    }
}