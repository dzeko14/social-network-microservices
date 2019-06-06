package com.github.dzeko14.socialNetwork.messagingService.repository

import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.interactors.repository.ChatRepository
import com.github.dzeko14.socialNetwork.messagingService.model.ChatImpl
import org.springframework.stereotype.Repository

@Repository
class ChatRepositoryImpl(
        private val jpaRepository: ChatJpaRepository
) : ChatRepository {
    override fun getChatsByUsersId(members: Set<Long>): List<Chat> {
        return jpaRepository.getChatImplsByMembersLike(HashSet(members))
    }

    override fun save(obj: Chat): Chat {
        return jpaRepository.save(ChatImpl(obj))
    }

    override fun delete(obj: Chat) {
        jpaRepository.delete(ChatImpl(obj))
    }

    override fun getAll(): List<Chat> {
        return jpaRepository.findAll()
    }

    override fun update(obj: Chat) {
        jpaRepository.save(ChatImpl(obj))
    }

    override fun getById(obj: Identifiable): Chat {
        return jpaRepository.findById(obj.id).get()
    }

    override fun validate(obj: Chat): Boolean {
        return jpaRepository.count() > 0
    }
}