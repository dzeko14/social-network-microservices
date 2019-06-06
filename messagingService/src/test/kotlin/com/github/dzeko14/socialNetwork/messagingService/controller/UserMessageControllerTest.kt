package com.github.dzeko14.socialNetwork.messagingService.controller

import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import com.github.dzeko14.socialNetwork.interactors.repository.ChatRepository
import com.github.dzeko14.socialNetwork.interactors.repository.UserMessageRepository
import com.github.dzeko14.socialNetwork.messagingService.controller.model.mockUser
import com.github.dzeko14.socialNetwork.messagingService.model.ChatImpl
import com.github.dzeko14.socialNetwork.messagingService.model.UserImpl
import com.github.dzeko14.socialNetwork.messagingService.model.UserMessageImpl
import com.github.dzeko14.socialNetwork.messagingService.repository.ChatJpaRepository
import com.github.dzeko14.socialNetwork.messagingService.repository.UserMessageJpaRepository
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
internal class UserMessageControllerTest {

    @Autowired
    lateinit var userMessageController: UserMessageController
    @Autowired
    lateinit var chatRepository: ChatRepository
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var messageRepository: UserMessageRepository
    @Autowired
    lateinit var userMessageJpaRepository: UserMessageJpaRepository

    lateinit var user: UserImpl

    @Before
    fun init() {
        user = userRepository.save(mockUser())
    }

    @Test
    fun getAll() {

        val chat = chatRepository.save(ChatImpl(0, "test", HashSet(listOf(user.id))))

        val msg1 = messageRepository.save(UserMessageImpl(0, "ee", user, 0, ChatImpl(chat)))
        val msg2 = messageRepository.save(UserMessageImpl(0, "ee", user, 0, ChatImpl(chat)))

        val testMsgs = userMessageController.getAll()
        Assert.assertTrue("GetAll not workin", testMsgs.all{ it.id == msg1.id || it.id == msg2.id } )
    }

    @Test
    fun getById() {

        val chat = chatRepository.save(ChatImpl(0, "test", HashSet(listOf(user.id))))

        val msg = messageRepository.save(UserMessageImpl(0, "ee", user, 0, ChatImpl(chat)))

        val testMsg = userMessageController.getById(msg.id)
        Assert.assertTrue("GetById not working", testMsg.id == msg.id )
    }

    @Test
    fun create() {
        val chat = chatRepository.save(ChatImpl(0, "test", HashSet(listOf(user.id))))
        val msg = userMessageController.create(UserMessageImpl(0, "ee", user, 0, ChatImpl(chat)))
        val testMsg = messageRepository.getById(IdentifiableImpl(msg.id))
        Assert.assertTrue("Create not working", testMsg.id == msg.id)
    }

    @Test
    fun delete() {
        val chat = chatRepository.save(ChatImpl(0, "test", HashSet(listOf(user.id))))
        val msg = messageRepository.save(UserMessageImpl(0, "ee", user, 0, ChatImpl(chat)))
        userMessageController.delete(msg.id)

        Assert.assertEquals(userMessageJpaRepository.countById(msg.id), 0L)
    }
}