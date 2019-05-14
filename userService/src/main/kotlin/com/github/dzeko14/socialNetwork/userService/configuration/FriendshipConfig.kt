package com.github.dzeko14.socialNetwork.userService.configuration

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.interactors.friendship.FriendshipInnteractors
import com.github.dzeko14.socialNetwork.provider.FriendshipProvider
import com.github.dzeko14.socialNetwork.provider.StorageProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FriendshipConfig {
    @Bean
    fun friendshipInteractors(
            userStorage: StorageProvider<User>,
            friendshipProvider: FriendshipProvider
    ): FriendshipInnteractors {
        return FriendshipInnteractors(userStorage, friendshipProvider)
    }
}