package com.github.dzeko14.socialNetwork.messageConsumer.model

import javax.persistence.*

@Entity
class MessageLog(
        var message: String = "",
        var messageId: Long = 0,
        var messageContent: String = "",
        var messageAuthorId: Long = 0,
        var date: Long = 0,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
) {
}