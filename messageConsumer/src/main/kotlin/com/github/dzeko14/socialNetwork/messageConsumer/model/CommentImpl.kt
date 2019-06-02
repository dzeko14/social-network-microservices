package com.github.dzeko14.socialNetwork.messageConsumer.model

import com.github.dzeko14.socialNetwork.entities.Comment
import com.github.dzeko14.socialNetwork.entities.Post
import com.github.dzeko14.socialNetwork.messageConsumer.model.PostImpl
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Embeddable
class CommentImpl(
        override val id: Long = 0,
        override val content: String = "",
        @Embedded
        @AttributeOverrides(value = [
            AttributeOverride(name = "id", column = Column(name ="post_id")),
            AttributeOverride(name = "content", column = Column(name ="post_content")),
            AttributeOverride(name = "date", column = Column(name ="post_date"))
        ])
        override val post: PostImpl = PostImpl(Post.emptyObject()),
        override val date: Long = Date().time
) : Comment(id, content, post, date) {
    constructor(c: Comment): this(c.id, c.content, PostImpl(c.post), c.date)
}