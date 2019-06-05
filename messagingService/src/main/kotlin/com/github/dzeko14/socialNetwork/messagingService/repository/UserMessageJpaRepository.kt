package com.github.dzeko14.socialNetwork.messagingService.repository

import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.entities.UserMessage
import com.github.dzeko14.socialNetwork.messagingService.model.UserMessageImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserMessageJpaRepository : JpaRepository<UserMessageImpl, Long> {
    fun getByChat(chat: Chat): List<UserMessageImpl>

    fun countById(id: Long): Long
}