package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.interactors.RegisterUserInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.GetAllIdentifiableInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.GetIdentifiableInteractor
import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UserController(
        @Autowired val registerUserInteractor: RegisterUserInteractor,
        @Autowired val getUserByIdInteractor: GetIdentifiableInteractor<User>,
        @Autowired val getAllUsersInteractor: GetAllIdentifiableInteractor<User>
) {
    @PostMapping
    fun createUser(@RequestBody user: UserImpl): ResponseEntity<String> {
        return try {
            registerUserInteractor.register(user)
            ResponseEntity.ok().build<String>()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") id: Long): ResponseEntity<User> {
        return try {
            val user = getUserByIdInteractor.get(
                    object: Identifiable {
                        override val id: Long = id
                    }
            )

            ResponseEntity.ok(user)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build<User>()
        }
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<User>> {
        return try {
            val user = getAllUsersInteractor.getAll()
            ResponseEntity.ok(user)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build<List<User>>()
        }
    }
}