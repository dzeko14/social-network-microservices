package com.github.dzeko14.socialNetwork.messagingService.interactor

import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.interactors.chat.CreateChatInteractor
import com.github.dzeko14.socialNetwork.interactors.repository.ChatRepository
import org.springframework.stereotype.Component

@Component
class CreateChatInteractorImpl(
        private val chatRepository: ChatRepository
) : CreateChatInteractor {


    override fun createChat(chat: Chat): Chat {
        val chats = chatRepository.getChatsByUsersId(HashSet(chat.members))
        chats.forEach { c ->
            if(c.members == chat.members)
                return c
        }
        return chatRepository.save(chat)
    }
}