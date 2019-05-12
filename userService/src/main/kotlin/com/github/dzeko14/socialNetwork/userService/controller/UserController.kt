package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.interactors.user.RegisterUserInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.GetAllIdentifiableInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.GetIdentifiableInteractor
import com.github.dzeko14.socialNetwork.interactors.user.DeleteUserInteractor
import com.github.dzeko14.socialNetwork.interactors.user.UpdateUserInfoInteractor
import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import com.github.dzeko14.socialNetwork.userService.model.UserUpdateInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
        @Autowired val registerUserInteractor: RegisterUserInteractor,
        @Autowired val getUserByIdInteractor: GetIdentifiableInteractor<User>,
        @Autowired val getAllUsersInteractor: GetAllIdentifiableInteractor<User>,
        @Autowired val updateUserInfoInteractor: UpdateUserInfoInteractor,
        @Autowired val deleteUserInteractor: DeleteUserInteractor) {

    @PostMapping
    fun createUser(@RequestBody user: UserImpl): ResponseEntity<String> {
        return try {
            registerUserInteractor.register(user)
            ResponseEntity.ok().build<String>()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @GetMapping("/id/{id}")
    fun getUserById(@PathVariable("id") id: Long): ResponseEntity<Any> {
        return try {
            val user = getUserByIdInteractor.get(
                    object: Identifiable {
                        override val id: Long = id
                    }
            )

            ResponseEntity.ok(user)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        }
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<Any> {
        return try {
            val user = getAllUsersInteractor.getAll()
            ResponseEntity.ok(user)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @PutMapping
    fun updateUserInfo(@RequestBody userUpdateInfo: UserUpdateInfo): ResponseEntity<Any> {
        return try {
            updateUserInfoInteractor.update(userUpdateInfo.user, userUpdateInfo.password)
            ResponseEntity.ok().build<Any>()
        }  catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @DeleteMapping
    fun deleteUser(@RequestBody user: UserImpl): ResponseEntity<Any> {
        return try {
            deleteUserInteractor.delete(user)
            ResponseEntity.ok().build<Any>()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }
}