package com.github.dzeko14.socialNetwork.userService.provider

import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.userService.model.CommentImpl
import com.github.dzeko14.socialNetwork.userService.model.PostImpl
import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import com.github.dzeko14.socialNetwork.userService.repository.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CommentStorageProvider @Autowired constructor(
        private val commentsRepository: CommentRepository
) : DefaultDatabaseStorageProvider<Comment, CommentImpl>(commentsRepository) {
    override fun getObject(obj: Comment): CommentImpl {
        return CommentImpl(obj)
    }

    override fun updateObject(old: Comment, new: Comment): CommentImpl {
        return CommentImpl(
                old.id,
                new.content,
                PostImpl(old.post),
                old.date,
                UserImpl(old.author)
        )
    }

    override fun validate(obj: Comment): Boolean {
        return commentsRepository.countById(obj.id) > 0
    }
}