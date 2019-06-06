package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.ChatImpl
import com.github.dzeko14.socialNetwork.client.model.UserMessageImpl
import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.entities.UserMessage
import org.springframework.stereotype.Component

@Component
class MessagingServiceClientImpl(
        private val feignClient: MessagingServiceFeignClient
) : ChatClient, UserMessageClient {
    override fun getChatsContainsUser(id: Long): List<Chat> {
        return feignClient.getChatsContainsUser(id)
    }

    override fun getChatMessages(id: Long): List<UserMessage> {
        return feignClient.getChatMessages(id)
    }

    override fun getAllChats(): List<Chat> {
        return feignClient.getAllChats()
    }

    override fun getChatById(id: Long): Chat {
        return feignClient.getChatById(id)
    }

    override fun createChat(chat: ChatImpl): Chat {
        return feignClient.createChat(chat)
    }

    override fun deleteChat(id: Long) {
        feignClient.deleteChat(id)
    }

    override fun updateChat(id: Long, body: ChatImpl) {
        feignClient.updateChat(id, body)
    }

    override fun getAllUserMessages(): List<UserMessage> {
        return feignClient.getAllUserMessages()
    }

    override fun getUserMessageById(id: Long): UserMessage {
        return feignClient.getUserMessageById(id)
    }

    override fun createUserMessage(userMessage: UserMessageImpl): UserMessage {
        return feignClient.createUserMessage(userMessage)
    }

    override fun deleteUserMessage(id: Long) {
        feignClient.deleteUserMessage(id)
    }
}