package com.github.dzeko14.socialNetwork.client.controller

import com.github.dzeko14.socialNetwork.client.feignClient.PostClient
import com.github.dzeko14.socialNetwork.client.model.PostImpl
import com.github.dzeko14.socialNetwork.client.model.TokenRequest
import com.github.dzeko14.socialNetwork.client.validator.TokenValidator
import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import feign.FeignException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/posts")
class PostController @Autowired constructor(
    private val postClient: PostClient,
    private val tokenValidator: TokenValidator
) {
    @PostMapping
    fun createPost(@RequestBody post: TokenRequest<PostImpl>): Post {
        try {
            tokenValidator.validate(post)
            return postClient.createPost(post.body)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long, @RequestBody token: Token) {
        try {
            tokenValidator.validate(token)
            postClient.deletePost(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping
    fun getAllPosts(@RequestBody token: Token): List<Post> {
        return try {
            tokenValidator.validate(token)
            postClient.getPosts()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long, @RequestBody token: Token): Post {
        return try {
            tokenValidator.validate(token)
            postClient.getPostById(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @PutMapping
    fun updatePost(@RequestBody post: TokenRequest<PostImpl>) {
        try {
            tokenValidator.validate(post)
            postClient.updatePost(post.body)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping("/user/{id}")
    fun getUserPosts(@PathVariable id: Long, @RequestBody token: Token): List<Post> {
        try {
            tokenValidator.validate(token)
            return postClient.getUserPosts(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping("/user/{id}/friends")
    fun getUserFriendsPosts(@PathVariable id: Long, @RequestBody token: Token): List<Post> {
        try {
            tokenValidator.validate(token)
            return postClient.getUserFriendsPosts(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }
}