package com.github.dzeko14.socialNetwork.messageConsumer.repository

import com.github.dzeko14.socialNetwork.messageConsumer.model.MessageLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserMessageLogRepository : JpaRepository<MessageLog, Long> {
}