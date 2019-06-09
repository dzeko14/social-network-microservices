package com.github.dzeko14.socialNetwork.interactors.repository

import com.github.dzeko14.socialNetwork.entities.ChatMember
import com.github.dzeko14.socialNetwork.provider.StorageProvider

interface ChatMemberRepository : StorageProvider<ChatMember> {
    fun getChatsByUser(userId: Long): List<String>
    fun getChatMembersByChatName(chatName: String): List<ChatMember>
}