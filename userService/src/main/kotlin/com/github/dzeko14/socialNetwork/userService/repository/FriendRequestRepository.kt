package com.github.dzeko14.socialNetwork.userService.repository

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.userService.model.FriendRequestImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FriendRequestRepository : JpaRepository<FriendRequestImpl, Long> {

    fun countById(id: Long): Long

    fun findFriendRequestImplByReceiver(receiver: User): List<FriendRequestImpl>
}