package com.github.dzeko14.socialNetwork.userService.controller

import com.github.dzeko14.socialNetwork.interactors.friendship.GetUserFriendsInteractor
import com.github.dzeko14.socialNetwork.interactors.friendship.RemoveFriendshipInteractor
import com.github.dzeko14.socialNetwork.userService.model.FriendshipImpl
import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import com.github.dzeko14.socialNetwork.userService.repository.FriendshipRepository
import com.github.dzeko14.socialNetwork.userService.repository.UserRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.util.Assert

@RunWith(SpringRunner::class)
@SpringBootTest
internal class FriendshipControllerTest {

    @Autowired var friendshipController: FriendshipController? = null
    @Autowired var userRepository: UserRepository? = null
    @Autowired var friendshipRepository: FriendshipRepository? = null

    @Test
    fun getFriends() {
    }

    @Test
    fun deleteFriendship() {

        val user1 =  userRepository!!.save(UserImpl(
                login = "grgrg"
        ))

        val user2 =  userRepository!!.save(UserImpl(
                login = "qqqqq"
        ))

        val fr1 = friendshipRepository!!.save(FriendshipImpl(
                user1 = user1,
                user2 = user2
        ))

        val fr2 = friendshipRepository!!.save(FriendshipImpl(
                user1 = user2,
                user2 = user1
        ))



        friendshipController!!.deleteFriendship(user1.id, user2.id)
        Assert.isNull (friendshipRepository!!.findByIdOrNull(fr1.id), "Nope fr1")
        Assert.isNull (friendshipRepository!!.findByIdOrNull(fr2.id), "Nope fr2")
    }
}