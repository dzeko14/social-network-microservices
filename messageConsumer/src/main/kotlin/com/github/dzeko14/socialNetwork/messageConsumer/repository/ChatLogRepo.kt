package com.github.dzeko14.socialNetwork.messageConsumer.repository

import com.github.dzeko14.socialNetwork.messageConsumer.model.ChatLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatLogRepo : JpaRepository<ChatLog, Long> {
}