package com.github.dzeko14.socialNetwork.client.feignClient

import org.springframework.cloud.openfeign.FeignClient

@FeignClient("social-network-service")
interface SocialNetworkClient : UsersClient, AuthClient,
        FriendshipClient, PostClient, CommentClient