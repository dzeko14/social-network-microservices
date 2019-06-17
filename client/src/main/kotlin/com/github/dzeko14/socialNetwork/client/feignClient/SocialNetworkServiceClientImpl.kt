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
        private val socialNetworkServiceFeignClient: SocialNetworkServiceFeignClient
) : AuthClient, CommentClient, FriendshipClient, PostClient, UsersClient {

    override fun checkIfAdmin(token: Token): Boolean {
        return socialNetworkServiceFeignClient.checkIfAdminByToken(token)
    }

    override fun auth(token: Token): Boolean {
        return socialNetworkServiceFeignClient.auth(token)
    }

    override fun createComment(comment: Comment): CommentImpl {
        return socialNetworkServiceFeignClient.createComment(comment)
    }

    override fun getCommentById(id: Long): CommentImpl {
        return socialNetworkServiceFeignClient.getCommentById(id)
    }

    override fun getComments(): List<CommentImpl> {
        return socialNetworkServiceFeignClient.getComments()
    }

    override fun updateComment(comment: Comment) {
        socialNetworkServiceFeignClient.updateComment(comment)
    }

    override fun deleteComment(id: Long) {
        socialNetworkServiceFeignClient.deleteComment(id)
    }

    override fun getPostComments(id: Long): List<CommentImpl> {
        return socialNetworkServiceFeignClient.getPostComments(id)
    }

    override fun makeFriendRequest(requester: Long, target: Long) {
        socialNetworkServiceFeignClient.makeFriendRequest(requester, target)
    }

    override fun approveFriendRequest(id: Long) {
        socialNetworkServiceFeignClient.approveFriendRequest(id)
    }

    override fun denyFriendRequest(id: Long) {
        socialNetworkServiceFeignClient.denyFriendRequest(id)
    }

    override fun getUserFriendRequests(userId: Long): List<FriendRequestImpl> {
        return socialNetworkServiceFeignClient.getUserFriendRequests(userId)
    }

    override fun getFriends(userId: Long): List<UserImpl> {
        return socialNetworkServiceFeignClient.getFriends(userId)
    }

    override fun deleteFriendship(remover: Long, target: Long) {
        socialNetworkServiceFeignClient.deleteFriendship(remover, target)
    }

    override fun createPost(user: Post): PostImpl {
        return socialNetworkServiceFeignClient.createPost(user)
    }

    override fun getPostById(id: Long): PostImpl {
        return socialNetworkServiceFeignClient.getPostById(id)
    }

    override fun getPosts(): List<PostImpl> {
        return socialNetworkServiceFeignClient.getPosts()
    }

    override fun updatePost(post: Post) {
        socialNetworkServiceFeignClient.updatePost(post)
    }

    override fun deletePost(id: Long) {
        socialNetworkServiceFeignClient.deletePost(id)
    }

    override fun getUserPosts(id: Long): List<PostImpl> {
        return socialNetworkServiceFeignClient.getUserPosts(id)
    }

    override fun getUserFriendsPosts(id: Long): List<PostImpl> {
        return socialNetworkServiceFeignClient.getUserFriendsPosts(id)
    }

    override fun createUser(user: User): UserImpl {
        return socialNetworkServiceFeignClient.createUser(user)
    }

    override fun getUserById(userId: Long): UserImpl {
        return socialNetworkServiceFeignClient.getUserById(userId)
    }

    override fun getUsers(): List<UserImpl> {
        return socialNetworkServiceFeignClient.getUsers()
    }

    override fun updateUserInfo(userUpdateInfo: UserUpdateInfo) {
        socialNetworkServiceFeignClient.updateUserInfo(userUpdateInfo)
    }

    override fun deleteUser(user: UserImpl) {
        socialNetworkServiceFeignClient.deleteUser(user)
    }

    override fun loginUser(userLogin: UserLogin): Token {
        return socialNetworkServiceFeignClient.loginUser(userLogin)
    }

    override fun getUserIdByToken(token: Token): Long {
        return socialNetworkServiceFeignClient.getUserIdByToken(token)
    }
}