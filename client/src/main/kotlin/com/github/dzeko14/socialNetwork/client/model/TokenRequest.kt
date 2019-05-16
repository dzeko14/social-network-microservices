package com.github.dzeko14.socialNetwork.client.model

import com.github.dzeko14.socialNetwork.entities.Token

class TokenRequest<T>(
        val token: Token,
        val body: T)