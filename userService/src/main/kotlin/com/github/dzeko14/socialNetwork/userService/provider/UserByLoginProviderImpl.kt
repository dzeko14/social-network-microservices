package com.github.dzeko14.socialNetwork.userService.provider

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.provider.UserByLoginProvider
import com.github.dzeko14.socialNetwork.userService.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserByLoginProviderImpl(
        @Autowired private val userRepository: UserRepository
) : UserByLoginProvider {
    override fun getUserByLogin(login: String): User {
        return userRepository.findByLogin(login)
    }
}