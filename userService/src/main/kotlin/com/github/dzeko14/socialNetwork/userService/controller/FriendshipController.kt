package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.socialNetwork.exceptions.NoSuchUserException
import com.github.dzeko14.socialNetwork.interactors.friendship.GetUserFriendsInteractor
import com.github.dzeko14.socialNetwork.interactors.friendship.RemoveFriendshipInteractor
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/friends")
class FriendshipController @Autowired constructor(
        private val getUserFriendsInteractor: GetUserFriendsInteractor,
        private val removeFriendshipInteractor: RemoveFriendshipInteractor
) {
    @GetMapping("/{id}")
    fun getFriends(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(
                    getUserFriendsInteractor.get(IdentifiableImpl(id))
            )
        } catch (e: NoSuchUserException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @DeleteMapping("/{remover}/{target}")
    fun deleteFriendship(@PathVariable remover: Long, @PathVariable target: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(
                    removeFriendshipInteractor.remove(IdentifiableImpl(remover), IdentifiableImpl(target))
            )
        } catch (e: NoSuchUserException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }
}