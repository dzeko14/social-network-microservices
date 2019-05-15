package com.github.dzeko14.socialNetwork.interactors.comment

import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

interface GetPostCommentsInteractor {
    fun getPostComments(postId: Identifiable): List<Comment>
}