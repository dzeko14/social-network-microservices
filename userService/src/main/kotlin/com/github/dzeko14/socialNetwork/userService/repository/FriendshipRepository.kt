package com.github.dzeko14.socialNetwork.userService.repository

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.userService.model.FriendshipImpl
import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

interface FriendshipRepository : JpaRepository<FriendshipImpl, Long> {

    fun countById(id: Long): Long

    fun findAllByUser1(user1: User): List<FriendshipImpl>

    @Transactional
    fun deleteFriendshipImplByUser1AndUser2(user1: UserImpl, user2: UserImpl)
}