package com.github.dzeko14.socialNetwork.entities

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import java.time.LocalDateTime
import java.util.*

abstract class Comment(
        override val id: Long = 0,
        open val content: String = "",
        open val post: Post = Post.emptyObject(),
        open val date: Long = Date().time,
        open val author: User = User.emptyObject()
) : Identifiable {

    companion object{
        fun emptyObject(): Comment{
            return object : Comment(){}
        }
    }
}