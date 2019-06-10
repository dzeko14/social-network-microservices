package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.client.model.CommentLog
import com.github.dzeko14.socialNetwork.client.model.PostLog
import com.github.dzeko14.socialNetwork.client.model.UserLog
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("log-consumer")
interface LogClient {
    @RequestMapping(method = [RequestMethod.GET],
            value = ["/logs/user"])
    fun getUserLogs(): List<UserLog>

    @RequestMapping(method = [RequestMethod.GET],
            value = ["/logs/comments"])
    fun getCommentLogs(): List<CommentLog>

    @RequestMapping(method = [RequestMethod.GET],
            value = ["/logs/posts"])
    fun getPostLogs(): List<PostLog>
}