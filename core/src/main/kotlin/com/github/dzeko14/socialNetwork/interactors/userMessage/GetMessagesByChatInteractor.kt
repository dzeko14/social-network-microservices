package com.github.dzeko14.socialNetwork.interactors.userMessage

import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.entities.UserMessage

interface GetMessagesByChatInteractor {
    fun getMessagesByChatInteractor(chatId: Long): List<UserMessage>
}