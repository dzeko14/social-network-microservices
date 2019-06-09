package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.ChatMemberImpl
import com.github.dzeko14.socialNetwork.entities.ChatMember

interface ChatMemberClient {
    fun getAllChatMembers(): List<ChatMember>

    fun getChatMemberById(id: Long): ChatMember

    fun deleteChatMember(id: Long)

    fun updateChatMember(id: Long, body: ChatMemberImpl)
}