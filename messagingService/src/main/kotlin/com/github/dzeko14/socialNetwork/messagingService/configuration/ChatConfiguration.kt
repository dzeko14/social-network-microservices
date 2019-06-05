package com.github.dzeko14.socialNetwork.messagingService.configuration

import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.interactors.crud.DefaultCrudInteractor
import com.github.dzeko14.socialNetwork.interactors.repository.ChatRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ChatConfiguration {
    @Bean
    fun defaultChatCrudInteractor(
            chatRepository: ChatRepository
    ): DefaultCrudInteractor<Chat> {
        return DefaultCrudInteractor(chatRepository)
    }
}