package com.github.dzeko14.socialNetwork.entities

open class Token (
        open val value: String
) {
    companion object {
        fun empty(): Token {
            return Token("")
        }
    }
}