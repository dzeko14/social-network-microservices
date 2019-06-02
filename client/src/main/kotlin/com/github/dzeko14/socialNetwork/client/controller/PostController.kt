package com.github.dzeko14.socialNetwork.client.controller

import com.github.dzeko14.socialNetwork.client.feignClient.PostClient
import com.github.dzeko14.socialNetwork.client.model.PostImpl
import com.github.dzeko14.socialNetwork.client.model.RabbitMQMessage
import com.github.dzeko14.socialNetwork.client.model.TokenRequest
import com.github.dzeko14.socialNetwork.client.validator.TokenValidator
import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.impl.IdentifiableImpl
import com.github.dzeko14.socialNetwork.rabbitmq.POST_QUEUE
import com.github.dzeko14.socialNetwork.rabbitmq.USER_QUEUE
import feign.FeignException
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/posts")
class PostController @Autowired constructor(
    private val postClient: PostClient,
    private val tokenValidator: TokenValidator,
    private val rabbitTemplate: RabbitTemplate
) {
    @PostMapping
    fun createPost(@RequestBody post: PostImpl,
                   @RequestParam token: String): Post {
        try {
           // tokenValidator.validate(Token(token))
            val p = postClient.createPost(post)
            rabbitTemplate.convertAndSend(POST_QUEUE, RabbitMQMessage("Post created", p))
            return p
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long, @RequestParam token: String) {
        try {
            //tokenValidator.validate(Token(token))
            val post = postClient.getPostById(id)
            postClient.deletePost(id)
            rabbitTemplate.convertAndSend(POST_QUEUE, RabbitMQMessage("Post deleted", post))
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping
    fun getAllPosts(@RequestParam token: String): List<Post> {
        return try {
           // tokenValidator.validate(Token(token))
            postClient.getPosts()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long, @RequestParam token: String): Post {
        return try {
            //tokenValidator.validate(Token(token))
            postClient.getPostById(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @PutMapping
    fun updatePost(@RequestBody post: PostImpl,
                   @RequestParam token: String) {
        try {
            //tokenValidator.validate(Token(token))
            postClient.updatePost(post)
            rabbitTemplate.convertAndSend(POST_QUEUE, RabbitMQMessage("Post updated", post))
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping("/user/{id}")
    fun getUserPosts(@PathVariable id: Long, @RequestParam token: String): List<Post> {
        try {
            //tokenValidator.validate(Token(token))
            return postClient.getUserPosts(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }

    @GetMapping("/user/{id}/friends")
    fun getUserFriendsPosts(@PathVariable id: Long, @RequestParam token: String): List<Post> {
        try {
            //tokenValidator.validate(Token(token))
            return postClient.getUserFriendsPosts(id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.valueOf(e.status()), e.contentUTF8())
        }
    }
}