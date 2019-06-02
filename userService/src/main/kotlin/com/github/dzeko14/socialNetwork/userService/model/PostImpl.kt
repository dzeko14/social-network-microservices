package com.github.dzeko14.socialNetwork.userService.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.entities.Post
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity(name = "Posts")
data class PostImpl(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        override val id: Long = 0,
        override val content: String,
        @ManyToOne(targetEntity = UserImpl::class)
        override val author: UserImpl,
        override val date: Long = Date().time
) : Post(id, content, author, date) {
    @JsonIgnore
    @OneToMany(targetEntity = CommentImpl::class, fetch = FetchType.EAGER, mappedBy = "post")
    override var comments: List<Comment>? = null

    constructor(p: Post): this(p.id, p.content, UserImpl(p.author), p.date)
}