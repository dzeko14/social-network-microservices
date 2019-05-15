package com.github.dzeko14.socialNetwork.interactors.comment

import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.exceptions.NoSuchObjectInStorageException
import com.github.dzeko14.socialNetwork.provider.StorageProvider

class CommentInteractorsImpl(
        private val postStorage: StorageProvider<Post>
) : GetPostCommentsInteractor {
    override fun getPostComments(postId: Identifiable): List<Comment> {
        val post = try {
            postStorage.getById(postId)
        } catch (e: Exception) {
            throw NoSuchObjectInStorageException(postId)
        }

        return post.comments ?: emptyList()
    }
}