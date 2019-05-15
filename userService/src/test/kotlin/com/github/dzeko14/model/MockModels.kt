package com.github.dzeko14.model

import com.github.dzeko14.socialNetwork.userService.model.PostImpl
import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import kotlin.random.Random

private val r = Random(0)

fun mockUser(): UserImpl {
    return UserImpl(
            login = "test${r.nextInt()}",
            password = "efeef",
            email = "qqqq",
            name = "test"
    )
}

fun mockPost(author: UserImpl, id: Long = 0): PostImpl {
    return PostImpl(
            id = id,
            content = "test",
            author = author
    )
}