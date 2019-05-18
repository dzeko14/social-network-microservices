package com.github.dzeko14.socialNetwork.messageConsumer.model

import com.github.dzeko14.socialNetwork.entities.Post
import java.time.LocalDateTime
import javax.persistence.*

@Embeddable
data class PostImpl(
        override val id: Long = 0,
        override val content: String,
        @Embedded
        @AttributeOverrides(value = [
            AttributeOverride(name = "id", column = Column(name ="user_id"))
        ])
        override val author: UserImpl,
        override val date: LocalDateTime = LocalDateTime.now()
) : Post(id, content, author, date) {

    constructor(p: Post): this(p.id, p.content, UserImpl(p.author), p.date)
}