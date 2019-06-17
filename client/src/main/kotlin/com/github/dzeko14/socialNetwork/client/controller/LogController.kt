package com.github.dzeko14.socialNetwork.client.controller

import com.github.dzeko14.socialNetwork.client.feignClient.LogClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/logs")
class LogController (
        private val logClient: LogClient
) {
    @GetMapping("/users")
    fun getUserLogs(): List<String> {
        return logClient.getUserLogs()
    }

    @GetMapping("/comments")
    fun getCommentLogs(): List<String> {
        return logClient.getCommentLogs()
    }

    @GetMapping("/posts")
    fun getPostLogs(): List<String> {
        return logClient.getPostLogs()
    }

    @GetMapping("/chats")
    fun getChatLogs(): List<String> {
        return logClient.getChatLogs()
    }

    @GetMapping("/messages")
    fun getMessageLogs(): List<String> {
        return logClient.getMessageLogs()
    }
}