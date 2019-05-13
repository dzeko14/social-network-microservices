package com.github.dzeko14.socialNetwork.userService.validator

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.userService.repository.UserRepository
import com.github.dzeko14.socialNetwork.validator.PasswordValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PasswordValidatorImpl(
        @Autowired private val userRepository: UserRepository
) : PasswordValidator {
    override fun validateUserPassword(user: User): Boolean {
        return try {
            val userFromDb = userRepository.findByLogin(user.login)
            userFromDb.password == user.password
        } catch (e: Exception) {
            false
        }
    }
}