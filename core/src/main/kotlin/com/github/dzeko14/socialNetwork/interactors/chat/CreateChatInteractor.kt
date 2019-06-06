package com.github.dzeko14.socialNetwork.interactors.chat

import com.github.dzeko14.socialNetwork.entities.Chat

interface CreateChatInteractor {
    fun createChat(chat: Chat): Chat
}