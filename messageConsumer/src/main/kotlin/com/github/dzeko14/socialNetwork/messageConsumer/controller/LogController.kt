package com.github.dzeko14.socialNetwork.messageConsumer.controller

import com.github.dzeko14.socialNetwork.messageConsumer.model.CommentLog
import com.github.dzeko14.socialNetwork.messageConsumer.model.PostLog
import com.github.dzeko14.socialNetwork.messageConsumer.model.UserLog
import com.github.dzeko14.socialNetwork.messageConsumer.repository.CommentLogRepository
import com.github.dzeko14.socialNetwork.messageConsumer.repository.PostLogRepository
import com.github.dzeko14.socialNetwork.messageConsumer.repository.UserLogRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/logs")
class LogController(
        private val userLogRepository: UserLogRepository,
        private val postLogRepository: PostLogRepository,
        private val commentLogRepository: CommentLogRepository
) {
    @GetMapping("/user")
    fun getUserLogs(): List<UserLog> {
        return userLogRepository.findAll()
    }

    @GetMapping("/comments")
    fun getCommentLogs(): List<CommentLog> {
        return commentLogRepository.findAll()
    }

    @GetMapping("/posts")
    fun getPostLogs(): List<PostLog> {
        return postLogRepository.findAll()
    }
}