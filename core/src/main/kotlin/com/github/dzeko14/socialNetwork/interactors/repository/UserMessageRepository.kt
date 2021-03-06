package com.github.dzeko14.socialNetwork.interactors.repository

import com.github.dzeko14.socialNetwork.entities.ChatMember
import com.github.dzeko14.socialNetwork.entities.UserMessage
import com.github.dzeko14.socialNetwork.provider.StorageProvider

interface UserMessageRepository : StorageProvider<UserMessage> {
    fun getMessagesByChat(chatName: String): List<UserMessage>
}