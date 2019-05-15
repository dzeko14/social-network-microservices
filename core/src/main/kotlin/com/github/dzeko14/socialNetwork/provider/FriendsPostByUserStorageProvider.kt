package com.github.dzeko14.socialNetwork.provider

import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.entities.User

interface FriendsPostByUserStorageProvider {
    fun getFriendsPostByUserStorageProvider(user: User): List<Post>
}