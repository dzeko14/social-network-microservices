package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.ChatMemberImpl
import com.github.dzeko14.socialNetwork.client.model.UserMessageImpl
import com.github.dzeko14.socialNetwork.entities.ChatMember
import com.github.dzeko14.socialNetwork.entities.UserMessage
import org.springframework.stereotype.Component

@Component
class MessagingServiceClientImpl(
        private val feignClient: MessagingServiceFeignClient
) : ChatClient, UserMessageClient, ChatMemberClient {
    override fun getChatsContainsUser(id: Long): List<String> {
        return feignClient.getChatsContainsUser(id)
    }

    override fun getChatMessages(chatName: String): List<UserMessage> {
        return feignClient.getChatMessages(chatName)
    }

    override fun createChat(chatMembers: List<ChatMemberImpl>) {
        feignClient.createChat(chatMembers)
    }

    override fun getAllChatMembers(): List<ChatMember> {
        return feignClient.getAllChatMembers()
    }

    override fun getChatMemberById(id: Long): ChatMember {
        return feignClient.getChatMemberById(id)
    }

    override fun deleteChatMember(id: Long) {
        feignClient.deleteChatMember(id)
    }

    override fun updateChatMember(id: Long, body: ChatMemberImpl) {
        TODO("Implement")
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