package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.ChatMemberImpl
import com.github.dzeko14.socialNetwork.entities.ChatMember
import com.github.dzeko14.socialNetwork.entities.UserMessage

interface ChatClient {
    fun getChatsContainsUser(id: Long): List<String>

    fun getChatMessages(chatName: String): List<UserMessage>

    fun createChat(chatMembers: List<ChatMemberImpl>)
}