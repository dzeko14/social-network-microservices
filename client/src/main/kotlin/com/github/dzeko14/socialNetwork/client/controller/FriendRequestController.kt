package com.github.dzeko14.socialNetwork.client.controller

import com.github.dzeko14.socialNetwork.client.feignClient.FriendshipClient
import com.github.dzeko14.socialNetwork.client.validator.TokenValidator
import com.github.dzeko14.socialNetwork.entities.FriendRequest
import com.github.dzeko14.socialNetwork.entities.Token
import feign.FeignException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/friendRequest")
class FriendRequestController @Autowired constructor(
        private val friendshipClient: FriendshipClient,
        private val tokenValidator: TokenValidator
) {
    @PostMapping("/create/{requester}/{target}")
    fun makeFriendRequest(@PathVariable requester: Long,
                          @PathVariable target: Long,
                          @RequestBody token: Token) {
        try {
            tokenValidator.validate(token)
            friendshipClient.makeFriendRequest(requester, target)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @PostMapping("/approve/{id}")
    fun approveFriendRequest(@PathVariable id: Long, @RequestBody token: Token) {
        return try {
            tokenValidator.validate(token)
            friendshipClient.approveFriendRequest(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @PostMapping("/deny/{id}")
    fun denyFriendRequest(@PathVariable id: Long, @RequestBody token: Token) {
        try {
            tokenValidator.validate(token)
            friendshipClient.denyFriendRequest(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping("/receiver/{id}")
    fun getUserFriendRequests(@PathVariable("id") userId: Long,
                              @RequestBody token: Token): List<FriendRequest> {
        return try {
            tokenValidator.validate(token)
            friendshipClient.getUserFriendRequests(userId)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }
}