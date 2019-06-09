package com.github.dzeko14.socialNetwork.client.model

import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.entities.User
import java.time.LocalDateTime
import java.util.*

class CommentImpl(
        override val id: Long = 0,
        override val content: String = "",
        override val post: PostImpl = PostImpl(Post.emptyObject()),
        override val date: Long = Date().time,
        override val author: UserImpl = UserImpl(User.emptyObject())
) : Comment(id, content, post, date, author) {
    constructor(c: Comment): this(c.id, c.content, PostImpl(c.post), c.date, UserImpl(c.author))
}