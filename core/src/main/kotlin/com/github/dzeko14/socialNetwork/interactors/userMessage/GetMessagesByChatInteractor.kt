package com.github.dzeko14.socialNetwork.interactors.userMessage

import com.github.dzeko14.socialNetwork.entities.UserMessage

interface GetMessagesByChatInteractor {
    fun getMessagesByChat(chatName: String): List<UserMessage>
}