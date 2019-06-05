package com.github.dzeko14.socialNetwork.interactors.repository

import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.provider.StorageProvider

interface ChatRepository : StorageProvider<Chat> {
    fun getChatsByUserId(userId: Long): List<Chat>
}