package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import com.github.dzeko14.socialNetwork.exceptions.NoSuchObjectInStorageException
import com.github.dzeko14.socialNetwork.interactors.crud.*
import com.github.dzeko14.socialNetwork.interactors.post.GetFriendsPostsInteractor
import com.github.dzeko14.socialNetwork.interactors.post.GetMyPostsInteractor
import com.github.dzeko14.socialNetwork.userService.model.PostImpl
import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
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

    @Value("\${post-content:%s}")
    private lateinit var contentFormatter: String

    @PostMapping
    fun createPost(@RequestBody post: PostImpl): Post {
        try {
            return createPostInteractor.create(post)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long) {
        try {
            deletePostInteractor.remove(IdentifiableImpl(id))
        } catch (e: NoSuchObjectInStorageException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @GetMapping
    fun getAllPosts(): List<Post> {
        return try {
            getAllIdentifiableInteractor.getAll()
        } catch (e: NoSuchObjectInStorageException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long): Post {
        return try {
            val p = getIdentifiableInteractor.get(IdentifiableImpl(id))
            PostImpl(
                    p.id,
                    String.format(contentFormatter, p.content),
                    UserImpl(p.author),
                    p.date
            )
        } catch (e: NoSuchObjectInStorageException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @PutMapping
    fun updatePost(@RequestBody post: PostImpl) {
        try {
            updateIdentifiableInteractor.update(post)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @GetMapping("/user/{id}")
    fun getUserPosts(@PathVariable id: Long): List<Post> {
        try {
             return getMyPostsInteractor.getPosts(IdentifiableImpl(id))
        } catch (e: NoSuchObjectInStorageException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @GetMapping("/user/{id}/friends")
    fun getUserFriendsPosts(@PathVariable id: Long): List<Post> {
        try {
            return getFriendsPostsInteractor.getFriendsPost(IdentifiableImpl(id))
        } catch (e: NoSuchObjectInStorageException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }
}