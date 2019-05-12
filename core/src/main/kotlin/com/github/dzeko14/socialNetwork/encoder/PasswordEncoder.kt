package com.github.dzeko14.socialNetwork.encoder

interface PasswordEncoder {
    fun encode(password: String): String
}