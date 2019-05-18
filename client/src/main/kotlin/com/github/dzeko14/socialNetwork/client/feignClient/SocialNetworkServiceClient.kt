package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.*
import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("social-network-service")
interface SocialNetworkServiceClient {
    @RequestMapping(method = [RequestMethod.POST],
            value = ["/auth"])
    fun auth(token: Token): Boolean

    @RequestMapping(method = [RequestMethod.POST], value = ["/comment"])
    fun createComment(comment: Comment): CommentImpl

    @RequestMapping(method = [RequestMethod.GET], value = ["/comment/{id}"])
    fun getCommentById(@PathVariable id: Long): CommentImpl

    @RequestMapping(method = [RequestMethod.GET], value = ["/comment"])
    fun getComments(): List<CommentImpl>

    @RequestMapping(method = [RequestMethod.PUT], value = ["/comment"])
    fun updateComment(comment: Comment)

    @RequestMapping(method = [RequestMethod.DELETE], value = ["/comment/{id}"])
    fun deleteComment(@PathVariable id: Long)

    @RequestMapping(method = [RequestMethod.GET], value = ["/comment/post/{id}"])
    fun getPostComments(@PathVariable id: Long): List<CommentImpl>

    @RequestMapping(method = [RequestMethod.POST],
            value = ["/friendRequest/create/{requester}/{target}"])
    fun makeFriendRequest(@PathVariable requester: Long, @PathVariable target: Long)

    @RequestMapping(method = [RequestMethod.POST],
            value = ["/friendRequest/approve/{id}"])
    fun approveFriendRequest(@PathVariable id: Long)

    @RequestMapping(method = [RequestMethod.POST],
            value = ["/friendRequest/deny/{id}"])
    fun denyFriendRequest(@PathVariable id: Long)

    @RequestMapping(method = [RequestMethod.GET],
            value = ["/friendRequest/receiver/{id}"])
    fun getUserFriendRequests(@PathVariable("id") userId: Long)
            : List<FriendRequestImpl>

    @RequestMapping(method = [RequestMethod.POST],
            value = ["/friends/{id}"])
    fun getFriends(@PathVariable("id") userId: Long)
            : List<UserImpl>

    @RequestMapping(method = [RequestMethod.DELETE],
            value = ["/friends/{remover}/{target}"])
    fun deleteFriendship(@PathVariable remover: Long, @PathVariable target: Long)

    @RequestMapping(method = [RequestMethod.POST], value = ["/posts"])
    fun createPost(user: Post): PostImpl

    @RequestMapping(method = [RequestMethod.GET], value = ["/posts/{id}"])
    fun getPostById(@PathVariable id: Long): PostImpl

    @RequestMapping(method = [RequestMethod.GET], value = ["/posts"])
    fun getPosts(): List<PostImpl>

    @RequestMapping(method = [RequestMethod.PUT], value = ["/posts"])
    fun updatePost(post: Post)

    @RequestMapping(method = [RequestMethod.DELETE], value = ["/posts/{id}"])
    fun deletePost(@PathVariable id: Long)

    @RequestMapping(method = [RequestMethod.GET], value = ["/posts/user/{id}"])
    fun getUserPosts(@PathVariable id: Long): List<PostImpl>

    @RequestMapping(method = [RequestMethod.GET], value = ["/posts/user/{id}/friends"])
    fun getUserFriendsPosts(@PathVariable id: Long): List<PostImpl>

    @RequestMapping(method = [RequestMethod.POST], value = ["/users"])
    fun createUser(user: User): UserImpl

    @RequestMapping(method = [RequestMethod.GET], value = ["/users/id/{userId}"])
    fun getUserById(@PathVariable("userId") userId: Long): UserImpl

    @RequestMapping(method = [RequestMethod.GET], value = ["/users"])
    fun getUsers(): List<UserImpl>

    @RequestMapping(method = [RequestMethod.PUT], value = ["/users"])
    fun updateUserInfo(userUpdateInfo: UserUpdateInfo)

    @RequestMapping(method = [RequestMethod.DELETE], value = ["/users"])
    fun deleteUser(user: UserImpl)

    @RequestMapping(method = [RequestMethod.POST], value = ["/users/login"])
    fun loginUser(userLogin: UserLogin): Token
}