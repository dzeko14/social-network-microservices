package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.ChatImpl
import com.github.dzeko14.socialNetwork.client.model.UserMessageImpl
import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.entities.UserMessage
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@FeignClient("messaging-service")
interface MessagingServiceFeignClient {

    @RequestMapping(method = [RequestMethod.GET],
            value = ["/chats/user/{id}"])
    fun getChatsContainsUser(@PathVariable id: Long): List<ChatImpl>

    @RequestMapping(method = [RequestMethod.GET],
            value = ["/chats/{id}/userMessages"])
    fun getChatMessages(@PathVariable id: Long): List<UserMessage>

    @RequestMapping(method = [RequestMethod.GET],
            value = ["/chats"])
    fun getAllChats(): List<ChatImpl>


    @RequestMapping(method = [RequestMethod.GET],
            value = ["/chats/{id}"])
    fun getChatById(@PathVariable id: Long): ChatImpl

    @RequestMapping(method = [RequestMethod.POST],
            value = ["/chats"])
    fun createChat(@RequestBody chat: ChatImpl): ChatImpl

    @RequestMapping(method = [RequestMethod.DELETE],
            value = ["/chats/{id}"])
    fun deleteChat(@PathVariable id: Long)

    @RequestMapping(method = [RequestMethod.PUT],
            value = ["/chats/{id}"])
    fun updateChat(@PathVariable id: Long, @RequestBody body: ChatImpl)

    @RequestMapping(method = [RequestMethod.GET],
            value = ["/userMessage"])
    fun getAllUserMessages(): List<UserMessageImpl>

    @RequestMapping(method = [RequestMethod.GET],
            value = ["/userMessage/{id}"])
    fun getUserMessageById(@PathVariable id: Long): UserMessageImpl

    @RequestMapping(method = [RequestMethod.POST],
            value = ["/userMessage"])
    fun createUserMessage(@RequestBody userMessage: UserMessageImpl): UserMessageImpl

    @RequestMapping(method = [RequestMethod.DELETE],
            value = ["/userMessage/{id}"])
    fun deleteUserMessage(@PathVariable id: Long)
}