package com.github.dzeko14.socialNetwork.client.model


class UserLog(
        var message: String = "",
        var user: UserImpl? = null,
        var date: Long = 0,
        var id: Long = 0
)