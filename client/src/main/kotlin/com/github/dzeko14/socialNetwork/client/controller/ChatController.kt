package com.github.dzeko14.socialNetwork.client.controller

import com.github.dzeko14.socialNetwork.client.feignClient.ChatClient
import com.github.dzeko14.socialNetwork.client.model.ChatImpl
import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.entities.UserMessage
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/chats")
class ChatController(
        private val chatClient: ChatClient
) {

    @GetMapping("/user/{id}")
    fun getChatsContainsUser(@PathVariable id: Long): List<Chat> {
        return try {
            //tokenValidator.validate(Token(token))
            chatClient.getChatsContainsUser(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping("/{id}/userMessages")
    fun getChatMessages(@PathVariable id: Long): List<UserMessage> {
        return try {
            //tokenValidator.validate(Token(token))
            chatClient.getChatMessages(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping
    fun getAll(@RequestParam token: String): List<Chat> {
        return try {
            //tokenValidator.validate(Token(token))
            chatClient.getAllChats()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }


    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Chat {
        return try {
            //tokenValidator.validate(Token(token))
            chatClient.getChatById(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @PostMapping
    fun create(@RequestBody chat: ChatImpl): Chat {
        return try {
            //tokenValidator.validate(Token(token))
            chatClient.createChat(chat)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.valueOf(500), e.message)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        return try {
            //tokenValidator.validate(Token(token))
            chatClient.deleteChat(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody body: ChatImpl) {
        try {
            //tokenValidator.validate(Token(token))
            chatClient.updateChat(id, body)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }
}