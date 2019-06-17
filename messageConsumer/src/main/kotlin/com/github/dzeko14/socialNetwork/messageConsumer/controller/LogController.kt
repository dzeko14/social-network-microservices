package com.github.dzeko14.socialNetwork.messageConsumer.controller

import com.github.dzeko14.socialNetwork.interactors.repository.UserMessageRepository
import com.github.dzeko14.socialNetwork.messageConsumer.model.CommentLog
import com.github.dzeko14.socialNetwork.messageConsumer.model.PostLog
import com.github.dzeko14.socialNetwork.messageConsumer.model.UserLog
import com.github.dzeko14.socialNetwork.messageConsumer.repository.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.text.SimpleDateFormat
import java.util.*

@RestController
@RequestMapping("/logs")
class LogController(
        private val userLogRepository: UserLogRepository,
        private val postLogRepository: PostLogRepository,
        private val commentLogRepository: CommentLogRepository,
        private val chatLogRepo: ChatLogRepo,
        private val messageRepository: UserMessageLogRepository
) {
    @GetMapping("/user")
    fun getUserLogs(): List<String> {
        return userLogRepository.findAll().map {
            val date = Date(it.date)
            val formatter = SimpleDateFormat("hh-mm-ss DD-MM-YY")
            val user = it?.user
            "${formatter.format(date)} --- ${it.message} --- userId: ${user?.id} --- userLogin:${user?.login}"
        }
    }

    @GetMapping("/comments")
    fun getCommentLogs(): List<String> {
        return commentLogRepository.findAll().map {
            val date = Date(it.date)
            val formatter = SimpleDateFormat("hh-mm-ss DD-MM-YY")
            val comment = it?.comment
            "${formatter.format(date)} --- ${it.message} --- commentId: ${comment?.id} --- commentContent:${comment?.content} --- commentAuthorId:${comment?.author?.id} --- commentPostId:${comment?.post?.id}"
        }
    }

    @GetMapping("/posts")
    fun getPostLogs(): List<String> {
        return postLogRepository.findAll().map {
            val date = Date(it.date)
            val formatter = SimpleDateFormat("hh-mm-ss DD-MM-YY")
            val post = it.post
            "${formatter.format(date)} --- ${it.message} --- postId: ${post?.id} --- postContent:${post?.content} --- postAuthorId:${post?.author?.id}"
        }
    }

    @GetMapping("/chats")
    fun getChatLogs(): List<String> {
        val logs = chatLogRepo.findAll()
        return logs.map {
            val date = Date(it.date)
            val formatter = SimpleDateFormat("hh-mm-ss DD-MM-YY")
            "${formatter.format(date)} --- ${it.message} --- ${it.id} --- ${it.chatName}"
        }
    }

    @GetMapping("/messages")
    fun getMessagesLogs(): List<String> {
        val logs = messageRepository.findAll()
        return logs.map {
            val date = Date(it.date)
            val formatter = SimpleDateFormat("hh-mm-ss DD-MM-YY")
            "${formatter.format(date)} --- ${it.message} --- messageId:${it.messageId} --- messageAuthorId:${it.messageAuthorId} --- messageContent: ${it.messageContent}"
        }
    }
}