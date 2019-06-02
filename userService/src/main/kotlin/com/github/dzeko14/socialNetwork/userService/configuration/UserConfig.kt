package com.github.dzeko14.socialNetwork.userService.configuration

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.interactors.user.*
import com.github.dzeko14.socialNetwork.provider.StorageProvider
import com.github.dzeko14.socialNetwork.provider.UserByLoginProvider
import com.github.dzeko14.socialNetwork.validator.PasswordValidator
import com.github.dzeko14.socialNetwork.validator.TokenValidator
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
    fun loginUserInteractor(tokenValidator: TokenValidator,
                            passwordValidator: PasswordValidator,
                            userByLoginProvider: UserByLoginProvider,
                            getUserIdByTokenInteractor: GetUserIdByTokenInteractor): LoginUserInteractor {
        return LoginUserInteractorImpl(passwordValidator, tokenValidator, userByLoginProvider,
                getUserIdByTokenInteractor)
    }

    @Bean
    fun validateUserInteractor(tokenValidator: TokenValidator): ValidateUserInteractor {
        return ValidateUserInteractorImpl(tokenValidator)
    }
}