package com.github.dzeko14.socialNetwork.entities

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import java.time.LocalDateTime

abstract class Comment(
        override val id: Long = 0,
        open val content: String = "",
        open val post: Post = Post.emptyObject(),
        open val date: LocalDateTime = LocalDateTime.now()
) : Identifiable {

    companion object{
        fun emptyObject(): Comment{
            return object : Comment(){}
        }
    }
}