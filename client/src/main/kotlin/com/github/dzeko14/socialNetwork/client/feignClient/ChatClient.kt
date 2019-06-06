package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.ChatImpl
import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.entities.UserMessage

interface ChatClient {
    fun getChatsContainsUser(id: Long): List<Chat>

    fun getChatMessages( id: Long): List<UserMessage>

    fun getAllChats(): List<Chat>

    fun getChatById(id: Long): Chat

    fun createChat(chat: ChatImpl): Chat

    fun deleteChat(id: Long)

    fun updateChat(id: Long, body: ChatImpl)
}