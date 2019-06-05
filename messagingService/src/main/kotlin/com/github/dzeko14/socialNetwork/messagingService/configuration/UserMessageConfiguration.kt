package com.github.dzeko14.socialNetwork.messagingService.configuration

import com.github.dzeko14.socialNetwork.entities.UserMessage
import com.github.dzeko14.socialNetwork.interactors.crud.DefaultCrudInteractor
import com.github.dzeko14.socialNetwork.interactors.repository.UserMessageRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserMessageConfiguration {
    @Bean
    fun defaultCrudInteractor(
            userMessageRepository: UserMessageRepository
    ) : DefaultCrudInteractor<UserMessage> {
        return DefaultCrudInteractor(userMessageRepository)
    }
}