package com.github.dzeko14.socialNetwork.entities

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import java.time.LocalDateTime
import java.util.*

abstract class Post(
        override val id: Long = 0,
        open val content: String = "",
        open val author: User = User.emptyObject(),
        open val date: Long = Date().time
) : Identifiable {

    open var comments: List<Comment>? = null

    companion object{
        fun emptyObject(): Post {
            return object : Post(){}
        }
    }
}