package com.github.dzeko14.socialNetwork.messagingService.configuration

import com.github.dzeko14.socialNetwork.entities.ChatMember
import com.github.dzeko14.socialNetwork.interactors.crud.DefaultCrudInteractor
import com.github.dzeko14.socialNetwork.interactors.repository.ChatMemberRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ChatConfiguration {
    @Bean
    fun defaultChatCrudInteractor(
            chatMemberRepository: ChatMemberRepository
    ): DefaultCrudInteractor<ChatMember> {
        return DefaultCrudInteractor(chatMemberRepository)
    }
}