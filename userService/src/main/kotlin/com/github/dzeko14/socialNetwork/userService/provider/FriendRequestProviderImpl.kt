package com.github.dzeko14.socialNetwork.userService.provider

import com.github.dzeko14.socialNetwork.entities.FriendRequest
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.provider.FriendRequestProvider
import com.github.dzeko14.socialNetwork.userService.repository.FriendRequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FriendRequestProviderImpl @Autowired constructor(
        private val friendRequestRepository: FriendRequestRepository
) : FriendRequestProvider {

    override fun getUsersFriendRequest(user: User): List<FriendRequest> {
        return friendRequestRepository.findFriendRequestImplByReceiver(user)
    }
}