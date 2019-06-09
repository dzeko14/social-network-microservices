package com.github.dzeko14.socialNetwork.interactors.chat

import com.github.dzeko14.socialNetwork.entities.ChatMember
import com.github.dzeko14.socialNetwork.entities.User

interface GetChatsContainsUserInteractor {
    fun getChatsThatContainsUser(userId: Long): List<String>
}