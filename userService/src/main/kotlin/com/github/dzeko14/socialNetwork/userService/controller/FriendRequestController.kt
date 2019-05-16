package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.socialNetwork.entities.FriendRequest
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.exceptions.NoSuchUserException
import com.github.dzeko14.socialNetwork.interactors.friendrequest.ApproveFriendRequestInteractor
import com.github.dzeko14.socialNetwork.interactors.friendrequest.DenyFriendRequestInteractor
import com.github.dzeko14.socialNetwork.interactors.friendrequest.GetUserFriendRequestInteractor
import com.github.dzeko14.socialNetwork.interactors.friendrequest.MakeFriendsRequestInteractor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/friendRequest")
class FriendRequestController @Autowired constructor(
        private val makeFriendsRequestInteractor: MakeFriendsRequestInteractor,
        private val approveFriendRequestInteractor: ApproveFriendRequestInteractor,
        private val denyFriendRequestInteractor: DenyFriendRequestInteractor,
        private val getUserFriendRequestInteractor: GetUserFriendRequestInteractor
) {
    @PostMapping("/create/{requester}/{target}")
    fun makeFriendRequest(@PathVariable requester: Long,
                          @PathVariable target: Long) {
        try {
            makeFriendsRequestInteractor.makeFriend(
                    IdentifiableImpl(requester),
                    IdentifiableImpl(target))
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @PostMapping("/approve/{id}")
    fun approveFriendRequest(@PathVariable id: Long) {
        return try {
            approveFriendRequestInteractor.approve(IdentifiableImpl(id))
        }
        catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @PostMapping("/deny")
    fun denyFriendRequest(@PathVariable id: Long) {
        try {
            denyFriendRequestInteractor.deny(IdentifiableImpl(id))
        }
        catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @GetMapping("/receiver/{id}")
    fun getUserFriendRequests(@PathVariable("id") userId: Long): List<FriendRequest> {
        return try {
            getUserFriendRequestInteractor.getFriendRequests(
                    object : Identifiable {
                        override val id: Long = userId
            })
        } catch (e: NoSuchUserException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }
}