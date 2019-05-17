package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.CommentImpl
import com.github.dzeko14.socialNetwork.entities.Comment
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

interface CommentClient {
    fun createComment(comment: Comment): CommentImpl

    fun getCommentById( id: Long): CommentImpl

    fun getComments(): List<CommentImpl>

    fun updateComment(comment: Comment)

    fun deleteComment( id: Long)

    fun getPostComments( id: Long): List<CommentImpl>

}