package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.UserImpl
import com.github.dzeko14.socialNetwork.client.model.UserLogin
import com.github.dzeko14.socialNetwork.client.model.UserUpdateInfo
import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.User

interface UsersClient {
    fun createUser(user: User): UserImpl

    fun getUserById(userId: Long): UserImpl

    fun getUsers(): List<UserImpl>

    fun updateUserInfo(userUpdateInfo: UserUpdateInfo)

    fun deleteUser(user: UserImpl)

    fun loginUser(userLogin: UserLogin): Token
}