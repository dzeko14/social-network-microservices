package com.github.dzeko14.socialNetwork.messagingService.repository

import com.github.dzeko14.socialNetwork.messagingService.model.UserImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserImpl, Long> {

    fun countById(id: Long): Long

    fun findByLogin(login: String): UserImpl
}