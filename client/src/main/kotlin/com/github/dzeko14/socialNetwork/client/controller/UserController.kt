package com.github.dzeko14.socialNetwork.client.controller

import com.github.dzeko14.socialNetwork.client.feignClient.AuthClient
import com.github.dzeko14.socialNetwork.client.feignClient.UsersClient
import com.github.dzeko14.socialNetwork.client.model.*
import com.github.dzeko14.socialNetwork.client.validator.TokenValidator
import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.rabbitmq.USER_QUEUE
import feign.FeignException
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(
        val usersClient: UsersClient,
        val tokenValidator: TokenValidator,
        private val rabbitTemplate: RabbitTemplate,
        private val authClient: AuthClient
) {

    @PostMapping("/id")
    fun getUserIdByToken(@RequestParam token: String): Long {
        return usersClient.getUserIdByToken(Token(token))
    }

    @PostMapping
    fun createUser(@RequestBody user: UserImpl) {
        val u = try {
            usersClient.createUser(user)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
        try {
            rabbitTemplate.convertAndSend(USER_QUEUE, RabbitMQMessage("User created", u))
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.valueOf(500), e.message)
        }
    }

    @GetMapping("/id/{id}")
    fun getUserById(@PathVariable("id") id: Long, @RequestParam token: String): User {
        return try {
           // tokenValidator.validate(Token(token))
            val u = usersClient.getUserById(id)
            rabbitTemplate.convertAndSend(USER_QUEUE, RabbitMQMessage("User get", u))
            u
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping
    fun getAllUsers(@RequestParam token: String): List<User> {
        return try {
            //tokenValidator.validate(Token(token))
            usersClient.getUsers()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @PutMapping
    fun updateUserInfo(@RequestBody tokenRequest: UserUpdateInfo,
                       @RequestParam token: String) {
        try {
            //tokenValidator.validate(Token(token))
            usersClient.updateUserInfo(tokenRequest)
            rabbitTemplate.convertAndSend(USER_QUEUE, RabbitMQMessage("User updated", tokenRequest.user))
        }  catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @DeleteMapping
    fun deleteUser(@RequestBody tokenRequest: UserImpl, @RequestParam token: String) {
        try {
            tokenValidator.validate(Token(token))
            usersClient.deleteUser(tokenRequest)
            rabbitTemplate.convertAndSend(USER_QUEUE, RabbitMQMessage("User deleted", tokenRequest))
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody userLogin: UserLogin): Token {
        return try {
            usersClient.loginUser(userLogin)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @PostMapping("/admin")
    fun checkIfAdmin(@RequestBody token: Token): Boolean {
        return try {
            authClient.checkIfAdmin(token)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }
}