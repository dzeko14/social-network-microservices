package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.exceptions.WrongPasswordException
import com.github.dzeko14.socialNetwork.interactors.user.RegisterUserInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.GetAllIdentifiableInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.GetIdentifiableInteractor
import com.github.dzeko14.socialNetwork.interactors.user.DeleteUserInteractor
import com.github.dzeko14.socialNetwork.interactors.user.LoginUserInteractor
import com.github.dzeko14.socialNetwork.interactors.user.UpdateUserInfoInteractor
import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import com.github.dzeko14.socialNetwork.userService.model.UserLogin
import com.github.dzeko14.socialNetwork.userService.model.UserUpdateInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(
        val registerUserInteractor: RegisterUserInteractor,
        val getUserByIdInteractor: GetIdentifiableInteractor<User>,
        val getAllUsersInteractor: GetAllIdentifiableInteractor<User>,
        val updateUserInfoInteractor: UpdateUserInfoInteractor,
        val deleteUserInteractor: DeleteUserInteractor,
        val loginUserInteractor: LoginUserInteractor
) {

    @PostMapping
    fun createUser(@RequestBody user: UserImpl) {
        try {
            registerUserInteractor.register(user)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }

    @GetMapping("/id/{id}")
    fun getUserById(@PathVariable("id") id: Long): User {
        return try {
           getUserByIdInteractor.get(
                    object: Identifiable {
                        override val id: Long = id
                    }
            )

           // ResponseEntity.ok(user)
        } catch (e: Exception) {
            //ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }

    @GetMapping
    fun getAllUsers(): List<User> {
        return try {
            getAllUsersInteractor.getAll()
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @PutMapping
    fun updateUserInfo(@RequestBody userUpdateInfo: UserUpdateInfo) {
        try {
            updateUserInfoInteractor.update(userUpdateInfo.user,
                    userUpdateInfo.password)
        }  catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @DeleteMapping
    fun deleteUser(@RequestBody user: UserImpl) {
        try {
            deleteUserInteractor.delete(user)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody userLogin: UserLogin): Token {
        return try {
            loginUserInteractor.login(userLogin.user, userLogin.token ?: Token.empty())
        } catch (e: WrongPasswordException) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, e.message)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }
}