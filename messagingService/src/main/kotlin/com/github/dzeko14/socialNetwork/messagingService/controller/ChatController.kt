package com.github.dzeko14.socialNetwork.messagingService.controller

import com.github.dzeko14.socialNetwork.entities.ChatMember
import com.github.dzeko14.socialNetwork.entities.UserMessage
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import com.github.dzeko14.socialNetwork.interactors.chat.CreateChatInteractor
import com.github.dzeko14.socialNetwork.interactors.chat.GetChatsContainsUserInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.*
import com.github.dzeko14.socialNetwork.interactors.userMessage.GetMessagesByChatInteractor
import com.github.dzeko14.socialNetwork.messagingService.model.ChatMemberImpl
import com.github.dzeko14.socialNetwork.messagingService.model.RabbitMQMessage
import com.github.dzeko14.socialNetwork.messagingService.model.UserImpl
import com.github.dzeko14.socialNetwork.messagingService.model.UserMessageImpl
import com.github.dzeko14.socialNetwork.rabbitmq.CHAT_QUEUE
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/chats")
class ChatController @Autowired constructor(
        private val getChatsInteractor: GetAllIdentifiableInteractor<ChatMember>,
        private val getChatByIdInteractor: GetIdentifiableInteractor<ChatMember>,
        private val createIdentifiableInteractor: CreateChatInteractor,
        private val updateIdentifiableInteractor: UpdateIdentifiableInteractor<ChatMember>,
        private val removeIdentifiableInteractor: RemoveIdentifiableInteractor<ChatMember>,
        private val getMessagesByChatInteractor: GetMessagesByChatInteractor,
        private val getChatsContainsUserInteractor: GetChatsContainsUserInteractor,
        private val rabbitTemplate: RabbitTemplate
) {

    @Value("\${chat-name:%s}")
    private lateinit var nameFormatter: String

    @Value("\${message-content:%s}")
    private lateinit var contentFormatter: String

    @GetMapping("/user/{id}")
    fun getChatsContainsUser(@PathVariable id: Long): List<String> {
        return try {
            getChatsContainsUserInteractor.getChatsThatContainsUser(id).map {
                String.format(nameFormatter, it)
            }
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @GetMapping("/{chatName}/userMessages")
    fun getChatMessages(@PathVariable chatName: String): List<UserMessage> {
        return try {
            val c = getMessagesByChatInteractor.getMessagesByChat(chatName).map { m ->
                UserMessageImpl(
                        m.id,
                        String.format(contentFormatter, m.content),
                        UserImpl(m.author),
                        m.date,
                        m.chatName
                )
            }
            rabbitTemplate.convertAndSend(CHAT_QUEUE, RabbitMQMessage("Get chat messages", chatName))
            c
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @GetMapping("/chatMembers")
    fun getAllChatMembers(): List<ChatMember> {
        return try {
            getChatsInteractor.getAll()
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @GetMapping("/chatMembers/{id}")
    fun getChatMemberById(@PathVariable id: Long): ChatMember {
        return try {
            getChatByIdInteractor.get(IdentifiableImpl(id))
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @PostMapping
    fun createChat(@RequestBody chatMembers: List<ChatMemberImpl>) {
        return try {
            val membersId = chatMembers.map { chatMember -> chatMember.user.id }
            createIdentifiableInteractor.createChat(chatMembers.first().name, membersId)
            rabbitTemplate.convertAndSend(CHAT_QUEUE, RabbitMQMessage("Chat created", chatMembers.first().name))
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @DeleteMapping("/chatMembers/{id}")
    fun deleteChatMember(@PathVariable id: Long) {
        try {
            removeIdentifiableInteractor.remove(IdentifiableImpl(id))
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @PutMapping("/{id}")
    fun updateChat(@PathVariable id: Long, @RequestBody body: ChatMemberImpl) {
        try {
           TODO("Implement")
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }
}