package com.github.dzeko14.socialNetwork.userService.model

import com.github.dzeko14.socialNetwork.entities.Post
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "Posts")
data class PostImpl(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        override val id: Long = 0,
        override val content: String,
        @ManyToOne(targetEntity = UserImpl::class)
        override val author: UserImpl,
        override val date: LocalDateTime = LocalDateTime.now()
) : Post(id, content, author, date) {
    constructor(p: Post): this(p.id, p.content, UserImpl(p.author), p.date)
}