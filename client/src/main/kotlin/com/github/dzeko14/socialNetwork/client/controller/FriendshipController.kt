package com.github.dzeko14.socialNetwork.client.controller

import com.github.dzeko14.socialNetwork.client.feignClient.FriendshipClient
import com.github.dzeko14.socialNetwork.client.validator.TokenValidator
import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.User
import feign.FeignException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/friends")
class FriendshipController @Autowired constructor(
        private val friendshipClient: FriendshipClient,
        private val tokenValidator: TokenValidator
) {
    @GetMapping("/{id}")
    fun getFriends(@PathVariable id: Long, @RequestParam token: String): List<User> {
        return try {
            //tokenValidator.validate(Token(token))
            friendshipClient.getFriends(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @DeleteMapping("/{remover}/{target}")
    fun deleteFriendship(@PathVariable remover: Long, @PathVariable target: Long,
                         @RequestParam token: String) {
        return try {
            //tokenValidator.validate(Token(token))
            friendshipClient.deleteFriendship(remover, target)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }
}