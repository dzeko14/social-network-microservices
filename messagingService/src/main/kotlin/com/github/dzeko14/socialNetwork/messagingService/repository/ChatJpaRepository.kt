package com.github.dzeko14.socialNetwork.messagingService.repository

import com.github.dzeko14.socialNetwork.messagingService.model.ChatImpl
import org.springframework.data.jpa.repository.JpaRepository

interface ChatJpaRepository : JpaRepository<ChatImpl, Long> {
    fun getChatImplsByMembers(id: Long): List<ChatImpl>

    fun countById(id: Long): Long
}