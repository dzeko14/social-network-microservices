package com.github.dzeko14.socialNetwork.userService.configuration

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.interactors.user.RegisterUserInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.DefaultCrudInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.GetAllIdentifiableInteractor
import com.github.dzeko14.socialNetwork.interactors.crud.GetIdentifiableInteractor
import com.github.dzeko14.socialNetwork.interactors.user.RegisterUserInteractorImpl
import com.github.dzeko14.socialNetwork.interactors.user.UserCrudInteractor
import com.github.dzeko14.socialNetwork.provider.StorageProvider
import com.github.dzeko14.socialNetwork.userService.repository.UserRepository
import com.github.dzeko14.socialNetwork.userService.validator.PasswordValidatorImpl
import com.github.dzeko14.socialNetwork.validator.PasswordValidator
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
    @Scope("singleton")
    fun userCrudInteractor(
            @Qualifier("userStorageProvider")
            userStorageProvider: StorageProvider<User>,
            passwordValidator: PasswordValidator
    ): UserCrudInteractor {
        return UserCrudInteractor(userStorageProvider, passwordValidator)
    }

    @Bean
    fun passwordValidator(userRepo: UserRepository): PasswordValidator {
        return PasswordValidatorImpl(userRepo)
    }
}