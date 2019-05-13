package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.interactors.user.ValidateUserInteractor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class UserAuthController @Autowired constructor(
        private val validateUserInteractor: ValidateUserInteractor
) {
    @PostMapping
    fun authUser(@RequestBody token: Token): Boolean {
        return try {
            validateUserInteractor.validate(token)
        } catch (e: Exception) {
            false
        }
    }
}