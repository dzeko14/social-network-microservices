package com.github.dzeko14.socialNetwork.client

import com.github.dzeko14.socialNetwork.client.model.UserImpl
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
