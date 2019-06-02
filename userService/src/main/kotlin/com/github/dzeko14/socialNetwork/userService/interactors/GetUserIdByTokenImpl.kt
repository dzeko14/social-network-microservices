package com.github.dzeko14.socialNetwork.userService.interactors

import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.interactors.user.GetUserIdByTokenInteractor
import org.springframework.stereotype.Component

@Component
class GetUserIdByTokenImpl : GetUserIdByTokenInteractor {
    override fun getUserIdByToken(token: Token): Long {
        val values = token.value.split("--")
        val id = values[1].toLongOrNull()
        return id ?: -1
    }
}