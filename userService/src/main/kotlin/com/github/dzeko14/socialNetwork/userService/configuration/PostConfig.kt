package com.github.dzeko14.socialNetwork.userService.configuration

import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.interactors.crud.DefaultCrudInteractor
import com.github.dzeko14.socialNetwork.interactors.friendship.GetUserFriendsInteractor
import com.github.dzeko14.socialNetwork.interactors.post.PostInteractorsImpl
import com.github.dzeko14.socialNetwork.userService.provider.PostStorageProvider
import com.github.dzeko14.socialNetwork.userService.provider.UserDatabaseStorageProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PostConfig {
    @Bean
    fun postCrudInteractor(postStorageProvider: PostStorageProvider): DefaultCrudInteractor<Post> {
        return DefaultCrudInteractor(postStorageProvider)
    }

    @Bean
    fun postInteractorsImpl(postStorageProvider: PostStorageProvider,
                            userStorageProvider: UserDatabaseStorageProvider,
                            getUserFriendsInteractor: GetUserFriendsInteractor): PostInteractorsImpl {
        return PostInteractorsImpl(userStorageProvider,
                getUserFriendsInteractor,
                postStorageProvider)
    }
}