package com.github.dzeko14.socialNetwork.client.controller

import com.github.dzeko14.socialNetwork.client.mockUser
import com.github.dzeko14.socialNetwork.client.model.ChatImpl
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
        val u1 = UserImpl(
                login = "test1",
                password = "efeef",
                email = "qqqq",
                name = "test"
        )
        userController.createUser(u1)
        val token1 = userController.loginUser(UserLogin(u1))
        val userId1 = userController.getUserIdByToken(token1.value)
        val user1 = userController.getUserById(userId1, token1.value)

        val u2 = UserImpl(
                login = "test2",
                password = "efeef",
                email = "qqqq",
                name = "test"
        )
        userController.createUser(u2)
        val token2 = userController.loginUser(UserLogin(u2))
        val userId2 = userController.getUserIdByToken(token2.value)
        val user2 = userController.getUserById(userId2, token2.value)

        val chat = chatController.create(ChatImpl(0,"efe", HashSet(listOf(user1.id, user2.id))))
        val msg = messageController.create(UserMessageImpl(0, "eefe", u1, 0, ChatImpl(chat)))

        val msgList = chatController.getChatMessages(chat.id)
        Assert.assertTrue(msgList.any{it.id == msg.id})
    }
}