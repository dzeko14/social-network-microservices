package com.github.dzeko14.socialNetwork.client.controller

import com.github.dzeko14.socialNetwork.client.feignClient.UserMessageClient
import com.github.dzeko14.socialNetwork.client.model.UserMessageImpl
import com.github.dzeko14.socialNetwork.entities.UserMessage
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/userMessages")
class UserMessageController (
        private val userMessageClient: UserMessageClient
) {
    @GetMapping
    fun getAll(): List<UserMessage> {
        return try {
            //tokenValidator.validate(Token(token))
            userMessageClient.getAllUserMessages()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): UserMessage {
        return try {
            //tokenValidator.validate(Token(token))
            userMessageClient.getUserMessageById(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @PostMapping
    fun create(@RequestBody userMessage: UserMessageImpl): UserMessage {
        return try {
            //tokenValidator.validate(Token(token))
            userMessageClient.createUserMessage(userMessage)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        try {
            //tokenValidator.validate(Token(token))
            userMessageClient.deleteUserMessage(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }
}