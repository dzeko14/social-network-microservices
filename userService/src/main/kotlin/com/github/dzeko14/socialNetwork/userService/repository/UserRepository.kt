package com.github.dzeko14.socialNetwork.userService.repository

import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserImpl, Long> {

    fun countById(id: Long): Long
}