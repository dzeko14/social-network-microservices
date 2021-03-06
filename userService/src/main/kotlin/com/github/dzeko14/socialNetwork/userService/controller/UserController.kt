package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.exceptions.WrongPasswordException
import com.github.dzeko14.socialNetwork.interactors.crud.GetAllIdentifiableInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.GetIdentifiableInteractor
import com.github.dzeko14.socialNetwork.interactors.user.*
import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import com.github.dzeko14.socialNetwork.userService.model.UserLogin
import com.github.dzeko14.socialNetwork.userService.model.UserUpdateInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RefreshScope
@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(
        val registerUserInteractor: RegisterUserInteractor,
        val getUserByIdInteractor: GetIdentifiableInteractor<User>,
        val getAllUsersInteractor: GetAllIdentifiableInteractor<User>,
        val updateUserInfoInteractor: UpdateUserInfoInteractor,
        val deleteUserInteractor: DeleteUserInteractor,
        val loginUserInteractor: LoginUserInteractor,
        val getUserIdByTokenInteractor: GetUserIdByTokenInteractor
) {

    @Value("\${login:%s}")
    private lateinit var loginFormatter: String

    @Value("\${name:%s}")
    private lateinit var nameFormatter: String

    @Value("\${email:%s}")
    private lateinit var emailFormatter: String

    @PostMapping("/id")
    fun getUserIdByToke(@RequestBody token: Token): Long {
        return getUserIdByTokenInteractor.getUserIdByToken(token)
    }

    @PostMapping
    fun createUser(@RequestBody user: UserImpl): User {
        try {
            return registerUserInteractor.register(user)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }

    @GetMapping("/id/{id}")
    fun getUserById(@PathVariable("id") id: Long): User {
        return try {
           val u = getUserByIdInteractor.get(
                    object: Identifiable {
                        override val id: Long = id
                    }
            )
            UserImpl(
                    u.id,
                    String.format(loginFormatter, u.login),
                    u.password,
                    String.format(emailFormatter, u.email),
                    String.format(nameFormatter, u.name)
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
            getAllUsersInteractor.getAll().map { u ->  UserImpl(
                    u.id,
                    String.format(loginFormatter, u.login),
                    u.password,
                    String.format(emailFormatter, u.email),
                    String.format(nameFormatter, u.name)
            )}
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