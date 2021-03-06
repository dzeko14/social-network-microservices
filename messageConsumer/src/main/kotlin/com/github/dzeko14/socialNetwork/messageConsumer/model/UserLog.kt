package com.github.dzeko14.socialNetwork.messageConsumer.model

import javax.persistence.*

@Entity
class UserLog(
        var message: String = "",
        @Embedded
        @AttributeOverrides(value = [
            AttributeOverride(name = "id", column = Column(name ="user_id"))
        ])
        var user: UserImpl? = null,
        var date: Long = 0,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
)