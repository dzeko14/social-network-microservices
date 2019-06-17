package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.interactors.user.GetUserIdByTokenInteractor
import com.github.dzeko14.socialNetwork.interactors.user.ValidateUserInteractor
import com.github.dzeko14.socialNetwork.userService.repository.AdminUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class UserAuthController @Autowired constructor(
        private val validateUserInteractor: ValidateUserInteractor,
        private val adminUserRepository: AdminUserRepository,
        val getUserIdByTokenInteractor: GetUserIdByTokenInteractor
) {
    @PostMapping
    fun authUser(@RequestBody token: Token): Boolean {
        return try {
            validateUserInteractor.validate(token)
        } catch (e: Exception) {
            false
        }
    }

    @PostMapping("/admin")
    fun authAdmin(@RequestBody token: Token): Boolean {
        return adminUserRepository
                .getAdminUsersByUserId(
                        getUserIdByTokenInteractor
                                .getUserIdByToken(token)
                ).isNotEmpty()
    }

}