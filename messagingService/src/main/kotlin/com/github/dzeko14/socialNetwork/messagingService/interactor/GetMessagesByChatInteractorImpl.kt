package com.github.dzeko14.socialNetwork.messagingService.interactor

import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.entities.UserMessage
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import com.github.dzeko14.socialNetwork.interactors.repository.ChatRepository
import com.github.dzeko14.socialNetwork.interactors.repository.UserMessageRepository
import com.github.dzeko14.socialNetwork.interactors.userMessage.GetMessagesByChatInteractor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GetMessagesByChatInteractorImpl @Autowired constructor(
        private val chatRepository: ChatRepository,
        private val userMessageRepository: UserMessageRepository
) : GetMessagesByChatInteractor {

    override fun getMessagesByChatInteractor(chatId: Long): List<UserMessage> {
        val chat = chatRepository.getById(IdentifiableImpl(chatId))
        return userMessageRepository.getMessagesByChat(chat)
    }
}