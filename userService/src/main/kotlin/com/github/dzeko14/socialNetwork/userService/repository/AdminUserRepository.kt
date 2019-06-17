package com.github.dzeko14.socialNetwork.userService.repository

import com.github.dzeko14.socialNetwork.userService.model.AdminUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdminUserRepository : JpaRepository<AdminUser, Long> {
    fun getAdminUsersByUserId(userId: Long): List<AdminUser>
}