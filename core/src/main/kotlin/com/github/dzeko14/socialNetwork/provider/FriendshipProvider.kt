package com.github.dzeko14.socialNetwork.provider

import com.github.dzeko14.socialNetwork.entities.Friendship
import com.github.dzeko14.socialNetwork.entities.User

interface FriendshipProvider {
    fun provideUserFriendships(user: User): List<Friendship>
    fun removeFriendshipBetweenUsers(user1: User, user2: User)
}