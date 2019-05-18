package com.github.dzeko14.socialNetwork.messageConsumer.model

import javax.persistence.*

@Entity
class PostLog(
        var message: String = "",
        @Embedded
        @AttributeOverrides(value = [
            AttributeOverride(name = "id", column = Column(name ="post_id")),
            AttributeOverride(name = "date", column = Column(name ="post_date"))
        ])
        var post: PostImpl? = null,
        var date: Long = 0,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
)