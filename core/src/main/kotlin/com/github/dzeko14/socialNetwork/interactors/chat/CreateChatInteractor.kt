package com.github.dzeko14.socialNetwork.interactors.chat

interface CreateChatInteractor {
    fun createChat(name: String, userIds: List<Long>)
}