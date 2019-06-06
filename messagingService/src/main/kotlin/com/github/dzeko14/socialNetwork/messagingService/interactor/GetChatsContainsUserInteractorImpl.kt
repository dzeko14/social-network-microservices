package com.github.dzeko14.socialNetwork.messagingService.interactor

import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.interactors.chat.GetChatsContainsUserInteractor
import com.github.dzeko14.socialNetwork.interactors.repository.ChatRepository
import org.springframework.stereotype.Component

@Component
class GetChatsContainsUserInteractorImpl(
        private val chatRepository: ChatRepository
) : GetChatsContainsUserInteractor {
    override fun getChatThatContainsUser(userid: Long): List<Chat> {
        return chatRepository.getChatsByUsersId(HashSet(listOf(userid)))
    }
}