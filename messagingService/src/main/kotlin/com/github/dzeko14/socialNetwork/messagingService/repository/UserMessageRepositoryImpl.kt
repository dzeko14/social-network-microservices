package com.github.dzeko14.socialNetwork.messagingService.repository

import com.github.dzeko14.socialNetwork.entities.UserMessage
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.interactors.repository.UserMessageRepository
import com.github.dzeko14.socialNetwork.messagingService.model.UserMessageImpl
import org.springframework.stereotype.Component

@Component
class UserMessageRepositoryImpl(
        private val jpaRepository: UserMessageJpaRepository
) : UserMessageRepository {

    override fun getMessagesByChat(chatName: String): List<UserMessage> {
        return jpaRepository.getUserMessageImplsByChatName(chatName)
    }

    override fun save(obj: UserMessage): UserMessage {
        return jpaRepository.save(UserMessageImpl(obj))
    }

    override fun delete(obj: UserMessage) {
        return jpaRepository.delete(UserMessageImpl(obj))
    }

    override fun getAll(): List<UserMessage> {
        return jpaRepository.findAll()
    }

    override fun update(obj: UserMessage) {
        jpaRepository.save(UserMessageImpl(obj))
    }

    override fun getById(obj: Identifiable): UserMessage {
        return jpaRepository.findById(obj.id).get()
    }

    override fun validate(obj: UserMessage): Boolean {
        return jpaRepository.countById(obj.id) > 0
    }
}