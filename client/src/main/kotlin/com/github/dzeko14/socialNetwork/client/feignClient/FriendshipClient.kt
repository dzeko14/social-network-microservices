package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.FriendRequestImpl
import com.github.dzeko14.socialNetwork.client.model.UserImpl
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

interface FriendshipClient {
    fun makeFriendRequest( requester: Long, target: Long)

    fun approveFriendRequest( id: Long)

    fun denyFriendRequest( id: Long)

    fun getUserFriendRequests( userId: Long)
            : List<FriendRequestImpl>

    fun getFriends(userId: Long)
            : List<UserImpl>

    fun deleteFriendship( remover: Long,  target: Long)
}