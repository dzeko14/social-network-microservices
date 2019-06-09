package com.github.dzeko14.socialNetwork.messagingService.interactor

import com.github.dzeko14.socialNetwork.entities.ChatMember
import com.github.dzeko14.socialNetwork.interactors.chat.GetChatsContainsUserInteractor
import com.github.dzeko14.socialNetwork.interactors.repository.ChatMemberRepository
import org.springframework.stereotype.Component

@Component
class GetChatsContainsUserInteractorImpl(
        private val chatMemberRepository: ChatMemberRepository
) : GetChatsContainsUserInteractor {
    override fun getChatsThatContainsUser(userId: Long): List<String> {
        return chatMemberRepository.getChatsByUser(userId)
    }
}