package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.FriendRequestImpl
import com.github.dzeko14.socialNetwork.client.model.UserImpl
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

interface FriendshipClient {
    @RequestMapping(method = [RequestMethod.POST],
            value = ["/friendRequest/create/{requester}/{target}"])
    fun makeFriendRequest(@PathVariable requester: Long, @PathVariable target: Long)

    @RequestMapping(method = [RequestMethod.POST],
            value = ["/friendRequest/approve/{id}"])
    fun approveFriendRequest(@PathVariable id: Long)

    @RequestMapping(method = [RequestMethod.POST],
            value = ["/friendRequest/deny/{id}"])
    fun denyFriendRequest(@PathVariable id: Long)

    @RequestMapping(method = [RequestMethod.GET],
            value = ["/friendRequest/receiver/{id}"])
    fun getUserFriendRequests(@PathVariable("id") userId: Long)
            : List<FriendRequestImpl>

    @RequestMapping(method = [RequestMethod.POST],
            value = ["/friends/{id}"])
    fun getFriends(@PathVariable("id") userId: Long)
            : List<UserImpl>

    @RequestMapping(method = [RequestMethod.DELETE],
            value = ["/friends/{remover}/{target}"])
    fun deleteFriendship(@PathVariable remover: Long, @PathVariable target: Long)
}