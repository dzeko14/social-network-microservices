package com.github.dzeko14.socialNetwork.client.controller

import com.github.dzeko14.socialNetwork.client.feignClient.CommentClient
import com.github.dzeko14.socialNetwork.client.model.CommentImpl
import com.github.dzeko14.socialNetwork.client.model.RabbitMQMessage
import com.github.dzeko14.socialNetwork.client.model.TokenRequest
import com.github.dzeko14.socialNetwork.client.validator.TokenValidator
import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import com.github.dzeko14.socialNetwork.rabbitmq.COMMENT_QUEUE
import com.github.dzeko14.socialNetwork.rabbitmq.POST_QUEUE
import feign.FeignException
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/comment")
class CommentController @Autowired constructor(
        private val commentClient: CommentClient,
        private val tokenValidator: TokenValidator,
        private val rabbitTemplate: RabbitTemplate
) {
    @PostMapping
    fun createComment(@RequestBody comment: TokenRequest<CommentImpl>): Comment {
        return try {
            tokenValidator.validate(comment)
            val c = commentClient.createComment(comment.body)
            rabbitTemplate.convertAndSend(COMMENT_QUEUE, RabbitMQMessage("Comment created", c))
            c
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable id: Long, @RequestBody token: Token) {
        try {
            tokenValidator.validate(token)
            val c = commentClient.getCommentById(id)
            commentClient.deleteComment(id)
            rabbitTemplate.convertAndSend(COMMENT_QUEUE, RabbitMQMessage("Comment deleted", c))
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping
    fun getAllComments(@RequestBody token: Token): List<Comment> {
        return try {
            tokenValidator.validate(token)
            commentClient.getComments()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping("/{id}")
    fun getCommentById(@PathVariable id: Long, @RequestBody token: Token): Comment {
        return try {
            tokenValidator.validate(token)
            commentClient.getCommentById(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @PutMapping
    fun updateComment(@RequestBody comment:  TokenRequest<CommentImpl>) {
        try {
            tokenValidator.validate(comment)
            commentClient.updateComment(comment.body)
            rabbitTemplate.convertAndSend(COMMENT_QUEUE, RabbitMQMessage("Comment updated", comment))
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping("/post/{id}")
    fun getPostComments(@PathVariable id: Long, @RequestBody token: Token): List<Comment> {
        return try {
            tokenValidator.validate(token)
            commentClient.getPostComments(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }
}