package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.ChatMemberImpl
import com.github.dzeko14.socialNetwork.client.model.UserMessageImpl
import com.github.dzeko14.socialNetwork.entities.UserMessage
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient("messaging-service")
interface MessagingServiceFeignClient {

    @RequestMapping(method = [RequestMethod.GET],
            value = ["/chats/user/{id}"])
    fun getChatsContainsUser(@PathVariable id: Long): List<String>

    @RequestMapping(method = [RequestMethod.GET],
            value = ["/chats/{chatName}/userMessages"])
    fun getChatMessages(@PathVariable chatName: String): List<UserMessageImpl>

    @RequestMapping(method = [RequestMethod.GET],
            value = ["/chats/chatMembers"])
    fun getAllChatMembers(): List<ChatMemberImpl>

    @RequestMapping(method = [RequestMethod.GET],
            value = ["/chats/chatMembers/{id}"])
    fun getChatMemberById(@PathVariable id: Long): ChatMemberImpl

    @RequestMapping(method = [RequestMethod.POST],
            value = ["/chats"])
    fun createChat(@RequestBody hatMembers: List<ChatMemberImpl>)

    @RequestMapping(method = [RequestMethod.DELETE],
            value = ["/chats/chatMembers/{id}"])
    fun deleteChatMember(@PathVariable id: Long)

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