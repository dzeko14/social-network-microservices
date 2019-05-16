package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.UserImpl
import com.github.dzeko14.socialNetwork.client.model.UserLogin
import com.github.dzeko14.socialNetwork.client.model.UserUpdateInfo
import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.User
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

interface UsersClient {
    @RequestMapping(method = [RequestMethod.POST], value = ["/users"])
    fun createUser(user: User)

    @RequestMapping(method = [RequestMethod.GET], value = ["/users/id/{userId}"])
    fun getUserById(@PathVariable("userId") userId: Long): UserImpl

    @RequestMapping(method = [RequestMethod.GET], value = ["/users"])
    fun getUsers(): List<UserImpl>

    @RequestMapping(method = [RequestMethod.PUT], value = ["/users"])
    fun updateUserInfo(userUpdateInfo: UserUpdateInfo)

    @RequestMapping(method = [RequestMethod.DELETE], value = ["/users"])
    fun deleteUser(user: UserImpl)

    @RequestMapping(method = [RequestMethod.POST], value = ["/users/login"])
    fun loginUser(userLogin: UserLogin): Token
}