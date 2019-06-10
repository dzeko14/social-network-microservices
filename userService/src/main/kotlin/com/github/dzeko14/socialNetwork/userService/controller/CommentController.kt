package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import com.github.dzeko14.socialNetwork.exceptions.NoSuchObjectInStorageException
import com.github.dzeko14.socialNetwork.interactors.comment.GetPostCommentsInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.*
import com.github.dzeko14.socialNetwork.userService.model.CommentImpl
import com.github.dzeko14.socialNetwork.userService.model.PostImpl
import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/comment")
class CommentController @Autowired constructor(
        private val createIdentifiableInteractor: CreateIdentifiableInteractor<Comment>,
        private val removeIdentifiableInteractor: RemoveIdentifiableInteractor<Comment>,
        private val getAllIdentifiableInteractor: GetAllIdentifiableInteractor<Comment>,
        private val getIdentifiableInteractor: GetIdentifiableInteractor<Comment>,
        private val updateIdentifiableInteractor: UpdateIdentifiableInteractor<Comment>,
        private val getPostCommentsInteractor: GetPostCommentsInteractor
) {

    @Value("\${comment-content:%s}")
    private lateinit var contentFormatter: String

    @PostMapping
    fun createComment(@RequestBody comment: CommentImpl): Comment {
        return try {
            createIdentifiableInteractor.create(comment)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable id: Long) {
        try {
            removeIdentifiableInteractor.remove(IdentifiableImpl(id))
        } catch (e: NoSuchObjectInStorageException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @GetMapping
    fun getAllComments(): List<Comment> {
        return try {
            getAllIdentifiableInteractor.getAll()
        } catch (e: NoSuchObjectInStorageException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @GetMapping("/{id}")
    fun getCommentById(@PathVariable id: Long): Comment {
        return try {
            val c = getIdentifiableInteractor.get(IdentifiableImpl(id))
            CommentImpl(
                    c.id,
                    String.format(contentFormatter, c.content),
                    PostImpl(c.post),
                    c.date,
                    UserImpl(c.author)
            )
        } catch (e: NoSuchObjectInStorageException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @PutMapping
    fun updateComment(@RequestBody comment: CommentImpl) {
        try {
            updateIdentifiableInteractor.update(comment)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @GetMapping("/post/{id}")
    fun getPostComments(@PathVariable id: Long): List<Comment> {
        return try {
            getPostCommentsInteractor.getPostComments(IdentifiableImpl(id))
        } catch (e: NoSuchObjectInStorageException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }
}