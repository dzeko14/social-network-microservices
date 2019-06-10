package com.github.dzeko14.socialNetwork.client.model

import com.github.dzeko14.socialNetwork.client.model.PostImpl

class PostLog(
        var message: String = "",

        var post: PostImpl? = null,
        var date: Long = 0,
        var id: Long = 0
)