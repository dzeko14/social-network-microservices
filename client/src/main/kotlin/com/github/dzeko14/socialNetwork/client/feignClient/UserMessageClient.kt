package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.UserMessageImpl
import com.github.dzeko14.socialNetwork.entities.UserMessage

interface UserMessageClient {
    fun getAllUserMessages(): List<UserMessage>

    fun getUserMessageById(id: Long): UserMessage

    fun createUserMessage(userMessage: UserMessageImpl): UserMessage

    fun deleteUserMessage(id: Long)
}