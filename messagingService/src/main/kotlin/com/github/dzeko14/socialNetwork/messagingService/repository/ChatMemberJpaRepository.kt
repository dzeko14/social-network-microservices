package com.github.dzeko14.socialNetwork.messagingService.repository

import com.github.dzeko14.socialNetwork.messagingService.model.ChatMemberImpl
import com.github.dzeko14.socialNetwork.messagingService.model.UserImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ChatMemberJpaRepository : JpaRepository<ChatMemberImpl, Long> {
    @Query(value = "SELECT DISTINCT name From chatmembers WHERE user_id = ?1",
            nativeQuery = true)
    fun getDistinctNameByUser(userId: Long): List<String>

    fun countById(id: Long): Long

    fun getChatMemberImplsByName(name: String): List<ChatMemberImpl>
}