package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.model.mockPost
import com.github.dzeko14.model.mockUser
import com.github.dzeko14.socialNetwork.entities.Friendship
import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.userService.model.FriendshipImpl
import com.github.dzeko14.socialNetwork.userService.model.PostImpl
import com.github.dzeko14.socialNetwork.userService.repository.FriendshipRepository
import com.github.dzeko14.socialNetwork.userService.repository.PostRepository
import com.github.dzeko14.socialNetwork.userService.repository.UserRepository
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@Suppress("UNCHECKED_CAST")
internal class PostControllerTest {


    @Autowired lateinit var postRepository: PostRepository
    @Autowired lateinit var postController: PostController
    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var friendshipRepository: FriendshipRepository

    @Test
    fun createPost() {
        val user = userRepository.save(mockUser())
        val post = postController.createPost(mockPost(user)).body as Post

        Assert.assertNotNull("PostController.createPost failed",
                postRepository.findByIdOrNull(post.id))
    }

    @Test
    fun deletePost() {
        val user = userRepository.save(mockUser())
        val post = postRepository.save(mockPost(user))
        postController.deletePost(post.id)
        Assert.assertNull("PostController.deletePost failed",
                postRepository.findByIdOrNull(post.id))
    }

    @Test

    fun getAllPosts() {
        val user = userRepository.save(mockUser())
        postRepository.save(mockPost(user))
        postRepository.save(mockPost(user))
        val posts = (postController.getAllPosts().body as List<Post>)
        Assert.assertTrue("PostController.getAllPosts post1 failed", posts.any{p -> p.author.id == user.id})
    }

    @Test
    fun getPostById() {
        val user = userRepository.save(mockUser())
        val post = postRepository.save(mockPost(user))
        Assert.assertNotNull("PostController.getPostById failed",
              postController.getPostById(post.id))
    }

    @Test
    fun updatePost() {
        val user = userRepository.save(mockUser())
        val post = postRepository.save(mockPost(user))
        val newPost = PostImpl(
                post.id,
                "updated",
                post.author,
                post.date
        )
        postController.updatePost(newPost)
        val newPostFromDB = postRepository.findByIdOrNull(post.id)
        Assert.assertNotNull("PostController.updatePost failed", newPostFromDB)
        Assert.assertTrue("PostController.updatePost failed",
                newPostFromDB!!.content == newPost.content)
    }

    @Test
    fun getUserPosts() {
        val user = userRepository.save(mockUser())
        postRepository.save(mockPost(user))
        postRepository.save(mockPost(user))

        val posts = (postController.getUserPosts(user.id).body as List<Post>).map { PostImpl(it) }
        Assert.assertTrue("PostController.getUserPosts post1 failed",  posts.any{p -> p.author.id == user.id})
    }

    @Test
    fun getUserFriendsPosts() {
        val user = userRepository.save(mockUser())
        val userFriend = userRepository.save(mockUser())
        postRepository.save(mockPost(userFriend))
        postRepository.save(mockPost(userFriend))

        val friendShip = FriendshipImpl(
                user1 = user,
                user2 = userFriend
        )
        val friendshipReversed = FriendshipImpl(
                user2 = user,
                user1 = userFriend
        )

        friendshipRepository.save(friendShip)
        friendshipRepository.save(friendshipReversed)

        val posts = (postController.getUserFriendsPosts(user.id).body as List<Post>).map { PostImpl(it) }
        Assert.assertTrue("PostController.getUserPosts post1 failed", posts.any{p -> p.author.id == userFriend.id})
    }
}