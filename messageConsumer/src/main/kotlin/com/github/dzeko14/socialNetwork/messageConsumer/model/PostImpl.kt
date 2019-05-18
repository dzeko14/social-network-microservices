package com.github.dzeko14.socialNetwork.messageConsumer.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.entities.Post
import java.time.LocalDateTime

data class PostImpl(
        override val id: Long = 0,
        override val content: String,
        override val author: UserImpl,
        override val date: LocalDateTime = LocalDateTime.now()
) : Post(id, content, author, date) {
    @JsonIgnore
    override var comments: List<Comment>? = null

    constructor(p: Post): this(p.id, p.content, UserImpl(p.author), p.date)
}