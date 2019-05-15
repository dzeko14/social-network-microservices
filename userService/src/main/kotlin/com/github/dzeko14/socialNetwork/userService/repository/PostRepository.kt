package com.github.dzeko14.socialNetwork.userService.repository

import com.github.dzeko14.socialNetwork.userService.model.PostImpl
import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<PostImpl, Long> {

    fun countById(id: Long): Long

    fun findPostImplByAuthor(author: UserImpl): List<PostImpl>
}