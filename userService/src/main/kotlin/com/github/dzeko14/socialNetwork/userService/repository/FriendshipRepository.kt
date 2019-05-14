package com.github.dzeko14.socialNetwork.userService.repository

import com.github.dzeko14.socialNetwork.userService.model.FriendshipImpl
import org.springframework.data.jpa.repository.JpaRepository

interface FriendshipRepository : JpaRepository<FriendshipImpl, Long> {

    fun countById(id: Long): Long
}