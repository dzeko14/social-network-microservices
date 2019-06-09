package com.github.dzeko14.socialNetwork.messagingService.controller

import com.github.dzeko14.socialNetwork.entities.ChatMember
import com.github.dzeko14.socialNetwork.entities.UserMessage
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import com.github.dzeko14.socialNetwork.interactors.chat.CreateChatInteractor
import com.github.dzeko14.socialNetwork.interactors.chat.GetChatsContainsUserInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.*
import com.github.dzeko14.socialNetwork.interactors.userMessage.GetMessagesByChatInteractor
import com.github.dzeko14.socialNetwork.messagingService.model.ChatMemberImpl
import org.springframework.beans.factory.annotation.Autowired
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
        private val getChatsContainsUserInteractor: GetChatsContainsUserInteractor
) {

    @GetMapping("/user/{id}")
    fun getChatsContainsUser(@PathVariable id: Long): List<String> {
        return try {
            getChatsContainsUserInteractor.getChatsThatContainsUser(id)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @GetMapping("/{chatName}/userMessages")
    fun getChatMessages(@PathVariable chatName: String): List<UserMessage> {
        return try {
            getMessagesByChatInteractor.getMessagesByChat(chatName)
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