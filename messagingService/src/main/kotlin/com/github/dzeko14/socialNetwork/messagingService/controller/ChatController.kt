package com.github.dzeko14.socialNetwork.messagingService.controller

import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.entities.UserMessage
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import com.github.dzeko14.socialNetwork.interactors.chat.GetChatsContainsUserInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.*
import com.github.dzeko14.socialNetwork.interactors.userMessage.GetMessagesByChatInteractor
import com.github.dzeko14.socialNetwork.messagingService.model.ChatImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/chats")
class ChatController @Autowired constructor(
        private val getChatsInteractor: GetAllIdentifiableInteractor<Chat>,
        private val getChatByIdInteractor: GetIdentifiableInteractor<Chat>,
        private val createIdentifiableInteractor: CreateIdentifiableInteractor<Chat>,
        private val updateIdentifiableInteractor: UpdateIdentifiableInteractor<Chat>,
        private val removeIdentifiableInteractor: RemoveIdentifiableInteractor<Chat>,
        private val getMessagesByChatInteractor: GetMessagesByChatInteractor,
        private val getChatsContainsUserInteractor: GetChatsContainsUserInteractor
) {

    @GetMapping("/user/{id}")
    fun getChatsContainsUser(@PathVariable id: Long): List<Chat> {
        return try {
            getChatsContainsUserInteractor.getChatThatContainsUser(id)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @GetMapping("/{id}/userMessages")
    fun getChatMessages(@PathVariable id: Long): List<UserMessage> {
        return try {
            getMessagesByChatInteractor.getMessagesByChatInteractor(id)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @GetMapping
    fun getAll(): List<Chat> {
        return try {
            getChatsInteractor.getAll()
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }


    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Chat {
        return try {
            getChatByIdInteractor.get(IdentifiableImpl(id))
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @PostMapping
    fun create(@RequestBody chat: ChatImpl): Chat {
        return try {
            createIdentifiableInteractor.create(chat)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        try {
            removeIdentifiableInteractor.remove(IdentifiableImpl(id))
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody body: ChatImpl) {
        try {
            updateIdentifiableInteractor.update(body)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }
}