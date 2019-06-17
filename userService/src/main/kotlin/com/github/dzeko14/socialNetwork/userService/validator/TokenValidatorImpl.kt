package com.github.dzeko14.socialNetwork.userService.validator

import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.userService.repository.UserRepository
import com.github.dzeko14.socialNetwork.validator.TokenValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.Exception
import java.time.LocalDateTime
import java.time.ZoneOffset

const val TOKEN_EXPIRE_DAY = 1

@Component
class TokenValidatorImpl @Autowired constructor(
        private val userRepository: UserRepository
) : TokenValidator {
    override fun validateToken(token: Token): Boolean {
        val tokenData = token.value.split("--")
        return try {
            val user = userRepository.findByLogin(tokenData[0])
            val encodedPassword = encodePassword(user.password)
            if (encodedPassword == tokenData[2].toInt()) {
                if (TOKEN_EXPIRE_DAY == -1) { true }
                else {
                    LocalDateTime
                            .now()
                            .toEpochSecond(ZoneOffset.UTC) < tokenData[3].toLong()
                }
            } else { false }
        } catch (e: Exception) { return false }
    }

    override fun generateToken(user: User): Token {
        return object: Token(
                "${user.login}--${user.id}--${encodePassword(user.password)}--${generateExpireDate()}"
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