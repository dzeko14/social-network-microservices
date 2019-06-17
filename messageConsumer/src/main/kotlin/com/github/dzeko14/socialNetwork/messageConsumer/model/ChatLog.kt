package com.github.dzeko14.socialNetwork.messageConsumer.model

import javax.persistence.*

@Entity
class ChatLog(
        var message: String = "",
        var chatName: String? = "",
        var date: Long = 0,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
) {
}