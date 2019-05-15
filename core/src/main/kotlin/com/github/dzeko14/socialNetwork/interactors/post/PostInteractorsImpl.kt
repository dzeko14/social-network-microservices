package com.github.dzeko14.socialNetwork.interactors.post

import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.exceptions.NoSuchUserException
import com.github.dzeko14.socialNetwork.provider.FriendsPostByUserStorageProvider
import com.github.dzeko14.socialNetwork.provider.PostsByUserStorageProvider
import com.github.dzeko14.socialNetwork.provider.StorageProvider

class PostInteractorsImpl(
    private val userStorage: StorageProvider<User>,
    private val friendsPostByUserStorageProvider: FriendsPostByUserStorageProvider,
    private val postsByUserStorageProvider: PostsByUserStorageProvider
) : GetFriendsPostsInteractor, GetMyPostsInteractor {
    override fun getFriendsPost(userId: Identifiable): List<Post> {
        val user = try {
            userStorage.getById(userId)
        } catch (e: Exception) {
            throw NoSuchUserException()
        }

        return friendsPostByUserStorageProvider.getFriendsPostByUserStorageProvider(user)
    }

    override fun getPosts(userId: Identifiable): List<Post> {
        val user = try {
            userStorage.getById(userId)
        } catch (e: Exception) {
            throw NoSuchUserException()
        }

        return postsByUserStorageProvider.getPostsByUser(user)
    }
}