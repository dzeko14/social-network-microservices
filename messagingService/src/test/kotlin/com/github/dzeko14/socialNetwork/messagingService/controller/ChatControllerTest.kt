package com.github.dzeko14.socialNetwork.messagingService.controller

import com.github.dzeko14.socialNetwork.entities.UserMessage
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import com.github.dzeko14.socialNetwork.interactors.repository.ChatRepository
import com.github.dzeko14.socialNetwork.interactors.repository.UserMessageRepository
import com.github.dzeko14.socialNetwork.messagingService.controller.model.mockUser
import com.github.dzeko14.socialNetwork.messagingService.model.ChatImpl
import com.github.dzeko14.socialNetwork.messagingService.model.UserImpl
import com.github.dzeko14.socialNetwork.messagingService.model.UserMessageImpl
import com.github.dzeko14.socialNetwork.messagingService.repository.ChatJpaRepository
import com.github.dzeko14.socialNetwork.messagingService.repository.UserRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@Suppress("UNCHECKED_CAST")
internal class ChatControllerTest {

    @Autowired
    lateinit var chatController: ChatController
    @Autowired lateinit var chatRepository: ChatRepository
    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var messageRepository: UserMessageRepository
    @Autowired lateinit var chatJpaRepository: ChatJpaRepository

    lateinit var user: UserImpl

    @Before
    fun init() {
        user = userRepository.save(mockUser())
    }

    @Test
    fun getChatsContainsUser() {
        val chat = chatRepository.save(ChatImpl(0, "test", ArrayList(listOf(user.id))))
        val testChat = chatController.getChatsContainsUser(user.id)

        Assert.assertTrue("getChatsContainsUser not working", testChat.any{chat.id == it.id} )
    }

    @Test
    fun getChatMessages() {
        val chat = chatRepository.save(ChatImpl(0, "test", ArrayList(listOf(user.id))))
        val msg1 = messageRepository.save(UserMessageImpl(0, "ee", user, 0, ChatImpl(chat)))
        val msg2 = messageRepository.save(UserMessageImpl(0, "ee", user, 0, ChatImpl(chat)))
        val testMsg = chatController.getChatMessages(chat.id).map { UserMessageImpl(it) }
        Assert.assertTrue("getChatMessages not working msg1", testMsg.any { it.id == msg1.id })
        Assert.assertTrue("getChatMessages not working msg2", testMsg.any{ it.id == msg2.id })
    }

    @Test
    fun getAll() {

        val chat1 = chatRepository.save(ChatImpl(0, "test", ArrayList(listOf(user.id))))
        val chat2 = chatRepository.save(ChatImpl(0, "test", ArrayList(listOf(user.id))))

        chatRepository.save(chat1)
        chatRepository.save(chat2)

        val testChats = chatController.getAll()
        Assert.assertTrue("getAll chat1", testChats.any { chat1.id == it.id })
        Assert.assertTrue("getAll chat2", testChats.any { chat2.id == it.id } )
    }

    @Test
    fun getById() {

        val chat = chatRepository.save(ChatImpl(0, "test", ArrayList(listOf(user.id))))

        val testChat = chatController.getById(chat.id)
        Assert.assertTrue("GetById chat", testChat.id == chat.id)
    }

    @Test
    fun create() {
        val chat = chatController.create(ChatImpl(0, "test", ArrayList(listOf(user.id))))
        val testChat = chatRepository.getById(IdentifiableImpl(chat.id))
        Assert.assertTrue(testChat.id == chat.id)
    }

    @Test
    fun delete() {

        val chat = chatRepository.save(ChatImpl(0, "test", ArrayList(listOf(user.id))))

        chatController.delete(chat.id)

        Assert.assertEquals(chatJpaRepository.countById(chat.id), 0L)
    }

    @Test
    fun update() {

    }
}