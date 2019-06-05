package com.github.dzeko14.socialNetwork.interactors.chat

import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.entities.User

interface GetChatsContainsUserInteractor {
    fun getChatThatContainsUser(userid: Long): List<Chat>
}