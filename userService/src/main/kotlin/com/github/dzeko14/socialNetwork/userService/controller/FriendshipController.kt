package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.exceptions.NoSuchUserException
import com.github.dzeko14.socialNetwork.interactors.friendship.GetUserFriendsInteractor
import com.github.dzeko14.socialNetwork.interactors.friendship.RemoveFriendshipInteractor
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/friends")
class FriendshipController @Autowired constructor(
        private val getUserFriendsInteractor: GetUserFriendsInteractor,
        private val removeFriendshipInteractor: RemoveFriendshipInteractor
) {
    @GetMapping("/{id}")
    fun getFriends(@PathVariable id: Long): List<User> {
        return try {
            getUserFriendsInteractor.get(IdentifiableImpl(id))
        } catch (e: NoSuchUserException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @DeleteMapping("/{remover}/{target}")
    fun deleteFriendship(@PathVariable remover: Long, @PathVariable target: Long) {
        return try {
            removeFriendshipInteractor.remove(IdentifiableImpl(remover), IdentifiableImpl(target))
        } catch (e: NoSuchUserException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }
}