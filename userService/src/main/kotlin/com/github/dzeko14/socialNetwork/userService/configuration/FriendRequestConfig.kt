package com.github.dzeko14.socialNetwork.userService.configuration

import com.github.dzeko14.socialNetwork.entities.FriendRequest
import com.github.dzeko14.socialNetwork.entities.Friendship
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.interactors.friendrequest.GetUserFriendRequestInteractorImpl
import com.github.dzeko14.socialNetwork.interactors.friendrequest.MakeFriendsInteractors
import com.github.dzeko14.socialNetwork.provider.FriendRequestProvider
import com.github.dzeko14.socialNetwork.provider.StorageProvider
import com.github.dzeko14.socialNetwork.userService.provider.FriendRequestDatabaseStorageProvider
import com.github.dzeko14.socialNetwork.userService.provider.FriendshipDatabaseStorageProvider
import com.github.dzeko14.socialNetwork.userService.provider.UserDatabaseStorageProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FriendRequestConfig {
    @Bean
    fun makeFriendInteractor(
            friendshipDatabaseStorageProvider: StorageProvider<Friendship>,
            friendRequestDatabaseStorage: StorageProvider<FriendRequest>,
            userDatabaseStorageProvider: StorageProvider<User>
    ): MakeFriendsInteractors {
        return MakeFriendsInteractors(friendshipDatabaseStorageProvider,
                friendRequestDatabaseStorage,
                userDatabaseStorageProvider)
    }

    @Bean
    fun getUserFriendRequestInteractor(
            friendRequestProvider: FriendRequestProvider,
            userDatabaseStorageProvider: StorageProvider<User>
    ): GetUserFriendRequestInteractorImpl {
        return GetUserFriendRequestInteractorImpl(friendRequestProvider, userDatabaseStorageProvider)
    }
}