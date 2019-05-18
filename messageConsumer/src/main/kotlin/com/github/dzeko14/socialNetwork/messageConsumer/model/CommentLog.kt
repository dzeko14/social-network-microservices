package com.github.dzeko14.socialNetwork.messageConsumer.model

import javax.persistence.*

@Entity
class CommentLog(
        var message: String = "",
        @Embedded
        @AttributeOverrides(value = [
            AttributeOverride(name = "id", column = Column(name ="comment_id")),
            AttributeOverride(name = "date", column = Column(name ="comment_date"))

        ])
        var comment: CommentImpl? = null,
        var date: Long = 0,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
)