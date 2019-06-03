package com.github.dzeko14.socialNetwork.entities

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

abstract class Chat(
        override val id: Long = 0,
        open val name: String = ""
) : Identifiable {
    companion object {
        fun empty(): Chat {
            return object : Chat() {}
        }
    }
}