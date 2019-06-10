package com.github.dzeko14.socialNetwork.messagingService.controller

import com.github.dzeko14.socialNetwork.entities.UserMessage
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import com.github.dzeko14.socialNetwork.interactors.crud.CreateIdentifiableInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.GetAllIdentifiableInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.GetIdentifiableInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.RemoveIdentifiableInteractor
import com.github.dzeko14.socialNetwork.interactors.userMessage.GetMessagesByChatInteractor
import com.github.dzeko14.socialNetwork.messagingService.model.UserImpl
import com.github.dzeko14.socialNetwork.messagingService.model.UserMessageImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/userMessage")
class UserMessageController @Autowired constructor(
        private val getAllUserMessagesInteractor: GetAllIdentifiableInteractor<UserMessage>,
        private val getUserMessageByIdInteractor: GetIdentifiableInteractor<UserMessage>,
        private val removeUserMessageInteractor: RemoveIdentifiableInteractor<UserMessage>,
        private val createUserMessageInteractor: CreateIdentifiableInteractor<UserMessage>
) {

    @Value("\${message-content:%s}")
    private lateinit var contentFormatter: String

    @GetMapping
    fun getAll(): List<UserMessage> {
        return try {
            getAllUserMessagesInteractor.getAll().map { m ->
                UserMessageImpl(
                        m.id,
                        String.format(contentFormatter, m.content),
                        UserImpl(m.author),
                        m.date,
                        m.chatName
                )
            }
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): UserMessage {
        return try {
            val m = getUserMessageByIdInteractor.get(IdentifiableImpl(id))
            UserMessageImpl(
                    m.id,
                    String.format(contentFormatter, m.content),
                    UserImpl(m.author),
                    m.date,
                    m.chatName
            )
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @PostMapping
    fun create(@RequestBody userMessage: UserMessageImpl): UserMessage {
        return try {
            createUserMessageInteractor.create(userMessage)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        try {
            removeUserMessageInteractor.remove(IdentifiableImpl(id))
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }
}