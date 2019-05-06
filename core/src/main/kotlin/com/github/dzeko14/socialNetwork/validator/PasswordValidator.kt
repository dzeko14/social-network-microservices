package com.github.dzeko14.socialNetwork.validator

import com.github.dzeko14.socialNetwork.entities.User

interface PasswordValidator {
    fun validateUserPassword(user: User): Boolean
}