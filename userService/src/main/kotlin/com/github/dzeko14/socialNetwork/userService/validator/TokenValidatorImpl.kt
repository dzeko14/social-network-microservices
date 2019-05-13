package com.github.dzeko14.socialNetwork.userService.validator

import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.validator.TokenValidator
import org.springframework.stereotype.Component
import java.lang.Exception
import java.time.LocalDateTime
import java.time.ZoneOffset

const val TOKEN_EXPIRE_DAY = 1

@Component
class TokenValidatorImpl : TokenValidator {
    override fun validateToken(token: Token, user: User): Boolean {
        val tokenData = token.value.split("--")
        return try {
            if (tokenData[0] == user.login
                    && encodePassword(user.password) == tokenData[1].toInt()) {
                if (TOKEN_EXPIRE_DAY == -1) { true }
                else {
                    LocalDateTime
                            .now()
                            .toEpochSecond(ZoneOffset.UTC) < tokenData[2].toLong()
                }
            } else { false }
        } catch (e: Exception) { return false }
    }

    override fun generateToken(user: User): Token {
        return object: Token(
                "${user.login}--${encodePassword(user.password)}--${generateExpireDate()}"
        ){ }
    }

    private fun generateExpireDate(): Long {
        return LocalDateTime
                .now()
                .plusDays(TOKEN_EXPIRE_DAY.toLong())
                .toEpochSecond(ZoneOffset.UTC)
    }

    private fun encodePassword(pass: String): Int {
        return pass.map { it.toInt() }.reduce { acc, i -> acc + i }
    }
}