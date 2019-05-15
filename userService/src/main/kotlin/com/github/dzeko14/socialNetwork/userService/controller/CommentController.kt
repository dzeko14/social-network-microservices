package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import com.github.dzeko14.socialNetwork.exceptions.NoSuchObjectInStorageException
import com.github.dzeko14.socialNetwork.interactors.comment.GetPostCommentsInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.*
import com.github.dzeko14.socialNetwork.userService.model.CommentImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
    @PostMapping
    fun createComment(@RequestBody comment: CommentImpl): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(createIdentifiableInteractor.create(comment))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            removeIdentifiableInteractor.remove(IdentifiableImpl(id))
            ResponseEntity.ok().build<Any>()
        } catch (e: NoSuchObjectInStorageException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @GetMapping
    fun getAllComments(): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(getAllIdentifiableInteractor.getAll())
        } catch (e: NoSuchObjectInStorageException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @GetMapping("/{id}")
    fun getCommentById(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(getIdentifiableInteractor.get(IdentifiableImpl(id)))
        } catch (e: NoSuchObjectInStorageException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @PutMapping
    fun updateComment(@RequestBody comment: CommentImpl): ResponseEntity<Any> {
        return try {
            updateIdentifiableInteractor.update(comment)
            ResponseEntity.ok().build<Any>()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @GetMapping("/post/{id}")
    fun getPostComments(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(getPostCommentsInteractor.getPostComments(IdentifiableImpl(id)))
        } catch (e: NoSuchObjectInStorageException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }
}