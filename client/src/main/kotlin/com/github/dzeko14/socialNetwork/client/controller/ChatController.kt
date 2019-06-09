package com.github.dzeko14.socialNetwork.client.controller

import com.github.dzeko14.socialNetwork.client.feignClient.ChatClient
import com.github.dzeko14.socialNetwork.client.feignClient.ChatMemberClient
import com.github.dzeko14.socialNetwork.client.model.ChatMemberImpl
import com.github.dzeko14.socialNetwork.entities.ChatMember
import com.github.dzeko14.socialNetwork.entities.UserMessage
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/chats")
class ChatController(
        private val chatClient: ChatClient,
        private val chatMemberClient: ChatMemberClient
) {

    @GetMapping("/user/{id}")
    fun getChatsContainsUser(@PathVariable id: Long): List<String> {
        return try {
            //tokenValidator.validate(Token(token))
            chatClient.getChatsContainsUser(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping("/{chatId}/userMessages")
    fun getChatMessages(@PathVariable("chatId") chatId: String): List<UserMessage> {
        return try {
            //tokenValidator.validate(Token(token))
            chatClient.getChatMessages(chatId)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping("/chatMember")
    fun getAllUserMembers(@RequestParam token: String): List<ChatMember> {
        return try {
            //tokenValidator.validate(Token(token))
            chatMemberClient.getAllChatMembers()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }


    @GetMapping("/chatMember/{id}")
    fun getChatMemberById(@PathVariable id: Long): ChatMember {
        return try {
            //tokenValidator.validate(Token(token))
            chatMemberClient.getChatMemberById(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @PostMapping
    fun createChat(@RequestBody chatMember: List<ChatMemberImpl>) {
        try {
            //tokenValidator.validate(Token(token))
            chatClient.createChat(chatMember)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.valueOf(500), e.message)
        }
    }

    @DeleteMapping("/chatMember/{id}")
    fun delete(@PathVariable id: Long) {
        return try {
            //tokenValidator.validate(Token(token))
            chatMemberClient.deleteChatMember(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }
}