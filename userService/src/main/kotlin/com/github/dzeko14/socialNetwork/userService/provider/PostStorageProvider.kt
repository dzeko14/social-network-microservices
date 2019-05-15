package com.github.dzeko14.socialNetwork.userService.provider

import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.provider.PostsByUserStorageProvider
import com.github.dzeko14.socialNetwork.userService.model.PostImpl
import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import com.github.dzeko14.socialNetwork.userService.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PostStorageProvider @Autowired constructor(
        private val postRepository: PostRepository
) : DefaultDatabaseStorageProvider<Post, PostImpl>(postRepository),
        PostsByUserStorageProvider {
    override fun getObject(obj: Post): PostImpl {
        return PostImpl(obj)
    }

    override fun updateObject(old: Post, new: Post): PostImpl {
        return PostImpl(
                id = old.id,
                content = new.content,
                author = UserImpl(old.author),
                date = LocalDateTime.now()
        )
    }

    override fun validate(obj: Post): Boolean {
        return postRepository.countById(obj.id) > 0
    }

    override fun getPostsByUser(user: User): List<Post> {
        return postRepository.findPostImplByAuthor(UserImpl(user))
    }
}