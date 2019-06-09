package com.github.dzeko14.socialNetwork.userService.model

import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.entities.User
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity(name = "Comments")
class CommentImpl(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        override val id: Long = 0,
        override val content: String = "",
        @ManyToOne(targetEntity = PostImpl::class)
        override val post: PostImpl = PostImpl(Post.emptyObject()),
        override val date: Long = Date().time,
        @ManyToOne(targetEntity = UserImpl::class)
        override val author: UserImpl = UserImpl(User.emptyObject())
) : Comment(id, content, post, date, author) {
    constructor(c: Comment): this(c.id, c.content, PostImpl(c.post), c.date, UserImpl(c.author))
}