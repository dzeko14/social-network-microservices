package com.github.dzeko14.socialNetwork.messagingService.repository

import com.github.dzeko14.socialNetwork.entities.ChatMember
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.interactors.repository.ChatMemberRepository
import com.github.dzeko14.socialNetwork.messagingService.model.ChatMemberImpl
import org.springframework.stereotype.Repository

@Repository
class ChatMemberRepositoryImpl(
        private val userRepository: UserRepository,
        private val memberJpaRepository: ChatMemberJpaRepository
) : ChatMemberRepository {

    override fun getChatMembersByChatName(chatName: String): List<ChatMember> {
        return memberJpaRepository.getChatMemberImplsByName(chatName)
    }

    override fun getChatsByUser(userId: Long): List<String> {
        val user = userRepository.findById(userId).get()
        return memberJpaRepository.getDistinctNameByUser(user.id)
    }

    override fun save(obj: ChatMember): ChatMember {
        return memberJpaRepository.save(ChatMemberImpl(obj))
    }

    override fun delete(obj: ChatMember) {
        memberJpaRepository.delete(ChatMemberImpl(obj))
    }

    override fun getAll(): List<ChatMember> {
        return memberJpaRepository.findAll()
    }

    override fun update(obj: ChatMember) {
        memberJpaRepository.save(ChatMemberImpl(obj))
    }

    override fun getById(obj: Identifiable): ChatMember {
        return memberJpaRepository.findById(obj.id).get()
    }

    override fun validate(obj: ChatMember): Boolean {
        return memberJpaRepository.count() > 0
    }
}