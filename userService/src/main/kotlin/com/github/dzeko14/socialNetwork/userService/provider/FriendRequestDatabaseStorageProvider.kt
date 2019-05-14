package com.github.dzeko14.socialNetwork.userService.provider

import com.github.dzeko14.socialNetwork.entities.FriendRequest
import com.github.dzeko14.socialNetwork.userService.model.FriendRequestImpl
import com.github.dzeko14.socialNetwork.userService.repository.FriendRequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FriendRequestDatabaseStorageProvider @Autowired constructor(
        private val friendRequestRepository: FriendRequestRepository
) : DefaultDatabaseStorageProvider<FriendRequest, FriendRequestImpl>(friendRequestRepository) {
    override fun getObject(obj: FriendRequest): FriendRequestImpl {
        return FriendRequestImpl(obj)
    }

    override fun updateObject(old: FriendRequest, new: FriendRequest): FriendRequestImpl {
        return FriendRequestImpl(old)
    }

    override fun validate(obj: FriendRequest): Boolean {
        return friendRequestRepository.countById(obj.id) > 0
    }
}