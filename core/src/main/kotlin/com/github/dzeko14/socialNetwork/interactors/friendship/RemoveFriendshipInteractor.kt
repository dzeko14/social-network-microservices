package com.github.dzeko14.socialNetwork.interactors.friendship

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable


interface RemoveFriendshipInteractor {
    fun remove(removerId: Identifiable, targetId: Identifiable)
}