package com.github.dzeko14.socialNetwork.client.feignClient

import com.github.dzeko14.socialNetwork.entities.Token
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

interface AuthClient {
    @RequestMapping(method = [RequestMethod.POST],
            value = ["/auth"])
    fun auth(token: Token): Boolean
}