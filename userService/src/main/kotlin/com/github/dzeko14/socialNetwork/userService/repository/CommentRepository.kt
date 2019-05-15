package com.github.dzeko14.socialNetwork.userService.repository

import com.github.dzeko14.socialNetwork.userService.model.CommentImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<CommentImpl, Long> {
    fun countById(id: Long): Long
}