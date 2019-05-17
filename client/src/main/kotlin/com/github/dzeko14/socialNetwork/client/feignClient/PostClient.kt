package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.PostImpl
import com.github.dzeko14.socialNetwork.entities.Post

interface PostClient {
    fun createPost(user: Post): PostImpl

    fun getPostById( id: Long): PostImpl

    fun getPosts(): List<PostImpl>

    fun updatePost(post: Post)

    fun deletePost( id: Long)

    fun getUserPosts( id: Long): List<PostImpl>

    fun getUserFriendsPosts( id: Long): List<PostImpl>

}