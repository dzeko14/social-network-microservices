package com.github.dzeko14.socialNetwork.client.controller

import com.github.dzeko14.socialNetwork.client.model.ChatMemberImpl
import com.github.dzeko14.socialNetwork.client.model.UserImpl
import com.github.dzeko14.socialNetwork.client.model.UserLogin
import com.github.dzeko14.socialNetwork.client.model.UserMessageImpl
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@Suppress("UNCHECKED_CAST")
internal class ChatControllerTest {

    @Autowired lateinit var userController: UserController
    @Autowired lateinit var chatController: ChatController
    @Autowired lateinit var messageController: UserMessageController

    @Test
    fun getChatMessages() {

    }
}