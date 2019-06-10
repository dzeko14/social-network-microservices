package com.github.dzeko14.socialNetwork.client.model


class CommentLog(
        var message: String = "",
        var comment: CommentImpl? = null,
        var date: Long = 0,
        var id: Long = 0
)