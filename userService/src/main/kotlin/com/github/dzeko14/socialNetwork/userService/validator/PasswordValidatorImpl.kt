package com.github.dzeko14.socialNetwork.userService.validator

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.userService.repository.UserRepository
import com.github.dzeko14.socialNetwork.validator.PasswordValidator

class PasswordValidatorImpl(
        private val userRepository: UserRepository
) : PasswordValidator {
    override fun validateUserPassword(user: User): Boolean {
        return try {
            val userFromDb = userRepository.findById(user.id).get()
            userFromDb.password == user.password
        } catch (e: Exception) {
            false
        }
    }
}