package com.github.dzeko14.socialNetwork.client.model

import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.entities.Post
import java.time.LocalDateTime

class CommentImpl(
        override val id: Long = 0,
        override val content: String = "",
        override val post: PostImpl = PostImpl(Post.emptyObject()),
        override val date: LocalDateTime = LocalDateTime.now()
) : Comment(id, content, post, date) {
    constructor(c: Comment): this(c.id, c.content, PostImpl(c.post), c.date)
}