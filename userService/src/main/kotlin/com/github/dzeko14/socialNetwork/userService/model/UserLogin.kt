package com.github.dzeko14.socialNetwork.userService.model

import com.github.dzeko14.socialNetwork.entities.Token

class UserLogin(
        val user: UserImpl,
        val token: Token? = null
)