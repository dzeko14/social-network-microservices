package com.github.dzeko14.socialNetwork.userService.controller

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
                          @PathVariable target: Long): ResponseEntity<Any> {
        return try {
            makeFriendsRequestInteractor.makeFriend(
                    IdentifiableImpl(requester),
                    IdentifiableImpl(target))
            ResponseEntity.ok().build<Any>()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @PostMapping("/approve/{id}")
    fun approveFriendRequest(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            approveFriendRequestInteractor.approve(IdentifiableImpl(id))
            ResponseEntity.ok().build<Any>()
        }
        catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @PostMapping("/deny")
    fun denyFriendRequest(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            denyFriendRequestInteractor.deny(IdentifiableImpl(id))
            ResponseEntity.ok().build<Any>()
        }
        catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @GetMapping("/receiver/{id}")
    fun getUserFriendRequests(@PathVariable("id") userId: Long): ResponseEntity<Any> {
        return try {
            val result = getUserFriendRequestInteractor.getFriendRequests(
                    object : Identifiable {
                        override val id: Long = userId
            })
            ResponseEntity.ok(result)
        } catch (e: NoSuchUserException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }
}