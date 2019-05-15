package com.github.dzeko14.socialNetwork.provider

import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.entities.User

interface PostsByUserStorageProvider {
    fun getPostsByUser(user: User): List<Post>
}