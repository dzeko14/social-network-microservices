package com.github.dzeko14.socialNetwork.client.controller

import com.github.dzeko14.socialNetwork.client.feignClient.CommentClient
import com.github.dzeko14.socialNetwork.client.model.CommentImpl
import com.github.dzeko14.socialNetwork.client.model.TokenRequest
import com.github.dzeko14.socialNetwork.client.validator.TokenValidator
import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import feign.FeignException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/comment")
class CommentController @Autowired constructor(
        private val commentClient: CommentClient,
        private val tokenValidator: TokenValidator
) {
    @PostMapping
    fun createComment(@RequestBody comment: TokenRequest<CommentImpl>): Comment {
        return try {
            tokenValidator.validate(comment)
            commentClient.createComment(comment.body)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable id: Long, @RequestBody token: Token) {
        try {
            tokenValidator.validate(token)
            commentClient.deleteComment(id)
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