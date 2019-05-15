package com.github.dzeko14.socialNetwork.userService.configuration

import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.interactors.comment.CommentInteractorsImpl
import com.github.dzeko14.socialNetwork.interactors.crud.DefaultCrudInteractor
import com.github.dzeko14.socialNetwork.userService.provider.CommentStorageProvider
import com.github.dzeko14.socialNetwork.userService.provider.PostStorageProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommentConfig {
    @Bean
    fun commentCrudInteractor(commentStorageProvider: CommentStorageProvider): DefaultCrudInteractor<Comment> {
        return DefaultCrudInteractor(commentStorageProvider)
    }

    @Bean
    fun commentInteractorsImpl(postStorageProvider: PostStorageProvider): CommentInteractorsImpl {
        return CommentInteractorsImpl(postStorageProvider)
    }
}