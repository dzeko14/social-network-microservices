package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.*
import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SocialNetworkServiceClientImpl @Autowired constructor(
        private val socialNetworkServiceClient: SocialNetworkServiceClient
) : AuthClient, CommentClient, FriendshipClient, PostClient, UsersClient {
    override fun auth(token: Token): Boolean {
        return socialNetworkServiceClient.auth(token)
    }

    override fun createComment(comment: Comment): CommentImpl {
        return socialNetworkServiceClient.createComment(comment)
    }

    override fun getCommentById(id: Long): CommentImpl {
        return socialNetworkServiceClient.getCommentById(id)
    }

    override fun getComments(): List<CommentImpl> {
        return socialNetworkServiceClient.getComments()
    }

    override fun updateComment(comment: Comment) {
        socialNetworkServiceClient.updateComment(comment)
    }

    override fun deleteComment(id: Long) {
        socialNetworkServiceClient.deleteComment(id)
    }

    override fun getPostComments(id: Long): List<CommentImpl> {
        return socialNetworkServiceClient.getPostComments(id)
    }

    override fun makeFriendRequest(requester: Long, target: Long) {
        socialNetworkServiceClient.makeFriendRequest(requester, target)
    }

    override fun approveFriendRequest(id: Long) {
        socialNetworkServiceClient.approveFriendRequest(id)
    }

    override fun denyFriendRequest(id: Long) {
        socialNetworkServiceClient.denyFriendRequest(id)
    }

    override fun getUserFriendRequests(userId: Long): List<FriendRequestImpl> {
        return socialNetworkServiceClient.getUserFriendRequests(userId)
    }

    override fun getFriends(userId: Long): List<UserImpl> {
        return socialNetworkServiceClient.getFriends(userId)
    }

    override fun deleteFriendship(remover: Long, target: Long) {
        socialNetworkServiceClient.deleteFriendship(remover, target)
    }

    override fun createPost(user: Post): PostImpl {
        return socialNetworkServiceClient.createPost(user)
    }

    override fun getPostById(id: Long): PostImpl {
        return socialNetworkServiceClient.getPostById(id)
    }

    override fun getPosts(): List<PostImpl> {
        return socialNetworkServiceClient.getPosts()
    }

    override fun updatePost(post: Post) {
        socialNetworkServiceClient.updatePost(post)
    }

    override fun deletePost(id: Long) {
        socialNetworkServiceClient.deletePost(id)
    }

    override fun getUserPosts(id: Long): List<PostImpl> {
        return socialNetworkServiceClient.getUserPosts(id)
    }

    override fun getUserFriendsPosts(id: Long): List<PostImpl> {
        return socialNetworkServiceClient.getUserFriendsPosts(id)
    }

    override fun createUser(user: User): UserImpl {
        return socialNetworkServiceClient.createUser(user)
    }

    override fun getUserById(userId: Long): UserImpl {
        return socialNetworkServiceClient.getUserById(userId)
    }

    override fun getUsers(): List<UserImpl> {
        return socialNetworkServiceClient.getUsers()
    }

    override fun updateUserInfo(userUpdateInfo: UserUpdateInfo) {
        socialNetworkServiceClient.updateUserInfo(userUpdateInfo)
    }

    override fun deleteUser(user: UserImpl) {
        socialNetworkServiceClient.deleteUser(user)
    }

    override fun loginUser(userLogin: UserLogin): Token {
        return socialNetworkServiceClient.loginUser(userLogin)
    }
}