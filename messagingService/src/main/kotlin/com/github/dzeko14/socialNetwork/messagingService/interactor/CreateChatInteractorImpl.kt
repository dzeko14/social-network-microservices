package com.github.dzeko14.socialNetwork.messagingService.interactor

import com.github.dzeko14.socialNetwork.interactors.chat.CreateChatInteractor
import com.github.dzeko14.socialNetwork.interactors.repository.ChatMemberRepository
import com.github.dzeko14.socialNetwork.messagingService.model.ChatMemberImpl
import com.github.dzeko14.socialNetwork.messagingService.model.UserImpl
import com.github.dzeko14.socialNetwork.messagingService.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class CreateChatInteractorImpl(
        private val chatMemberRepository: ChatMemberRepository,
        private val userRepository: UserRepository
) : CreateChatInteractor {


    override fun createChat(name: String, userIds: List<Long>) {
        val savedChatMembers = chatMemberRepository.getChatMembersByChatName(name)
        val chatMembersIdToSave = userIds.filterNot { id ->
            savedChatMembers.any{
                it.user.id == id
            }
        }
        for (userId in chatMembersIdToSave) {
            userRepository.findById(userId).ifPresent {user ->
                chatMemberRepository.save(
                        ChatMemberImpl(0, name, UserImpl(user))
                )
            }

        }
    }
}