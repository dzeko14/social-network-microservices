package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.PostImpl
import com.github.dzeko14.socialNetwork.entities.Post
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

interface PostClient {
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

}