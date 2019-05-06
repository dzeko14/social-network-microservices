package com.github.dzeko14.socialNetwork.userService.configuration

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.interactors.RegisterUserInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.DefaultCrudInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.GetAllIdentifiableInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.GetIdentifiableInteractor
import com.github.dzeko14.socialNetwork.interactors.implementations.RegisterUserInteractorImpl
import com.github.dzeko14.socialNetwork.provider.StorageProvider
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class UserConfig {
    @Bean
    fun registerUserInteractor(
            @Qualifier("userStorageProvider")
            userStorageProvider: StorageProvider<User>
    ): RegisterUserInteractor {
        return RegisterUserInteractorImpl(userStorageProvider)
    }

    @Bean
    fun getUserByIdInteractor(
            @Qualifier("userCrudInteractor")
            userCrudInteractor: DefaultCrudInteractor<User>
    ): GetIdentifiableInteractor<User> {
        return userCrudInteractor
    }

    @Bean
    fun getAllUsersInteractor(
            @Qualifier("userCrudInteractor")
            userCrudInteractor: DefaultCrudInteractor<User>
    ): GetAllIdentifiableInteractor<User> {
        return userCrudInteractor
    }

    @Qualifier("userCrudInteractor")
    @Bean
    @Scope("singleton")
    fun getDefaultUserCrudInteractor(
            @Qualifier("userStorageProvider")
            userStorageProvider: StorageProvider<User>
    ): DefaultCrudInteractor<User> {
        return DefaultCrudInteractor(userStorageProvider)
    }
}