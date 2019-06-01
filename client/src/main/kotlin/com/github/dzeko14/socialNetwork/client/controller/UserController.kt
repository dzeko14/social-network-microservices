package com.github.dzeko14.socialNetwork.client.controller

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
        private val rabbitTemplate: RabbitTemplate
) {

    @GetMapping("/id")
    fun getUserIdByToken(@RequestBody token: Token): Long {

    }

    @PostMapping
    fun createUser(@RequestBody user: UserImpl) {
        val u = try {
            usersClient.createUser(user)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
        rabbitTemplate.convertAndSend(USER_QUEUE, RabbitMQMessage("User created", u))
    }

    @GetMapping("/id/{id}")
    fun getUserById(@PathVariable("id") id: Long, @RequestBody token: Token): User {
        return try {
            tokenValidator.validate(token)
            val u = usersClient.getUserById(id)
            rabbitTemplate.convertAndSend(USER_QUEUE, RabbitMQMessage("User get", u))
            u
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping
    fun getAllUsers(@RequestBody token: Token): List<User> {
        return try {
            tokenValidator.validate(token)
            usersClient.getUsers()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @PutMapping
    fun updateUserInfo(@RequestBody tokenRequest: TokenRequest<UserUpdateInfo>) {
        try {
            tokenValidator.validate(tokenRequest)
            usersClient.updateUserInfo(tokenRequest.body)
            rabbitTemplate.convertAndSend(USER_QUEUE, RabbitMQMessage("User updated", tokenRequest.body.user))
        }  catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @DeleteMapping
    fun deleteUser(@RequestBody tokenRequest: TokenRequest<UserImpl>) {
        try {
            tokenValidator.validate(tokenRequest)
            usersClient.deleteUser(tokenRequest.body)
            rabbitTemplate.convertAndSend(USER_QUEUE, RabbitMQMessage("User deleted", tokenRequest.body))
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
}