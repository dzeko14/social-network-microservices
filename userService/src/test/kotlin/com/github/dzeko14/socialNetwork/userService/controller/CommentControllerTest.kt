package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.model.mockComment
import com.github.dzeko14.model.mockPost
import com.github.dzeko14.model.mockUser
import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.userService.model.CommentImpl
import com.github.dzeko14.socialNetwork.userService.model.PostImpl
import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import com.github.dzeko14.socialNetwork.userService.repository.CommentRepository
import com.github.dzeko14.socialNetwork.userService.repository.PostRepository
import com.github.dzeko14.socialNetwork.userService.repository.UserRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@Suppress("UNCHECKED_CAST")
internal class CommentControllerTest {

    @Autowired lateinit var commentController: CommentController
    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var postRepository: PostRepository
    @Autowired lateinit var commentRepository: CommentRepository

    private lateinit var author: UserImpl
    private lateinit var post: PostImpl

    @Before
    fun init() {
        author = userRepository.save(mockUser())
        post = postRepository.save(mockPost(author))
    }

    @Test
    fun createComment() {
        val comment = commentController.createComment(mockComment(post)).body as Comment

        Assert.assertNotNull("CommentController.createComment failed",
                commentRepository.findByIdOrNull(comment.id))
    }

    @Test
    fun deleteComment() {
        val comment = commentRepository.save(mockComment(post))
        commentController.deleteComment(comment.id)

        Assert.assertNull("CommentController.deleteComment failed",
                commentRepository.findByIdOrNull(comment.id))
    }

    @Test
    fun getAllComments() {
        commentRepository.save(mockComment(post))
        commentRepository.save(mockComment(post))

        val comments = (commentController.getAllComments().body as List<Comment>)
        Assert.assertTrue("CommentController.getAllComments failed", comments.any{ c -> c.post.id == post.id})
    }

    @Test
    fun getCommentById() {
        val comment =  commentRepository.save(mockComment(post))
        Assert.assertNotNull("CommentController.getCommentById failed",
                commentController.getCommentById(comment.id))
    }

    @Test
    fun updateComment() {
        val comment = commentRepository.save(mockComment(post))
        val newComment = CommentImpl(
                comment.id,
                "update",
                comment.post,
                comment.date
        )
        commentController.updateComment(newComment)
        val newCommentFromDB = commentRepository.findByIdOrNull(comment.id)
        Assert.assertNotNull("CommentController.updateComment failed", newCommentFromDB)
        Assert.assertTrue("CommentController.updateComment failed",
                newCommentFromDB!!.content == newComment.content)
    }

    @Test
    fun getPostComments() {
        commentRepository.save(mockComment(post))
        commentRepository.save(mockComment(post))

        val comments = (commentController.getPostComments(post.id).body as List<Comment>)
        Assert.assertTrue("CommentController.getPostComments failed", comments.any{ c -> c.post.id == post.id})
    }
}