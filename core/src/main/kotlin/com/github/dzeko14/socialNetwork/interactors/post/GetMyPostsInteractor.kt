package com.github.dzeko14.socialNetwork.interactors.post

import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

interface GetMyPostsInteractor {
    fun getPosts(userId: Identifiable): List<Post>
}