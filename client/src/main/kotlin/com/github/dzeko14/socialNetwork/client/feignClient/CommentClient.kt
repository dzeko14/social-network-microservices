package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.CommentImpl
import com.github.dzeko14.socialNetwork.entities.Comment
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

interface CommentClient {
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

}