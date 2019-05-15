package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import com.github.dzeko14.socialNetwork.exceptions.NoSuchObjectInStorageException
import com.github.dzeko14.socialNetwork.interactors.crud.*
import com.github.dzeko14.socialNetwork.interactors.post.GetFriendsPostsInteractor
import com.github.dzeko14.socialNetwork.interactors.post.GetMyPostsInteractor
import com.github.dzeko14.socialNetwork.userService.model.PostImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController
@RequestMapping("/posts")
class PostController @Autowired constructor(
    private val createPostInteractor: CreateIdentifiableInteractor<Post>,
    private val deletePostInteractor: RemoveIdentifiableInteractor<Post>,
    private val getAllIdentifiableInteractor: GetAllIdentifiableInteractor<Post>,
    private val getIdentifiableInteractor: GetIdentifiableInteractor<Post>,
    private val updateIdentifiableInteractor: UpdateIdentifiableInteractor<Post>,
    private val getMyPostsInteractor: GetMyPostsInteractor,
    private val getFriendsPostsInteractor: GetFriendsPostsInteractor
) {
    @PostMapping
    fun createPost(@RequestBody post: PostImpl): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(createPostInteractor.create(post))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            deletePostInteractor.remove(IdentifiableImpl(id))
            ResponseEntity.ok().build<Any>()
        } catch (e: NoSuchObjectInStorageException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @GetMapping
    fun getAllPosts(): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(getAllIdentifiableInteractor.getAll())
        } catch (e: NoSuchObjectInStorageException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(getIdentifiableInteractor.get(IdentifiableImpl(id)))
        } catch (e: NoSuchObjectInStorageException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @PutMapping
    fun updatePost(@RequestBody post: PostImpl): ResponseEntity<Any> {
        return try {
            updateIdentifiableInteractor.update(post)
            ResponseEntity.ok().build<Any>()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @GetMapping("/user/{id}")
    fun getUserPosts(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(getMyPostsInteractor.getPosts(IdentifiableImpl(id)))

        } catch (e: NoSuchObjectInStorageException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @GetMapping("/user/{id}/friends")
    fun getUserFriendsPosts(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(getFriendsPostsInteractor.getFriendsPost(IdentifiableImpl(id)))
        } catch (e: NoSuchObjectInStorageException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }
}