package com.github.dzeko14.socialNetwork.messageConsumer.repository

import com.github.dzeko14.socialNetwork.messageConsumer.model.PostLog
import org.springframework.data.jpa.repository.JpaRepository

interface PostLogRepository : JpaRepository<PostLog, Long>