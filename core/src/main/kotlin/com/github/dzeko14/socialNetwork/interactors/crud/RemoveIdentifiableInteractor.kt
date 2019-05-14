package com.github.dzeko14.socialNetwork.interactors.crud

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

interface RemoveIdentifiableInteractor<T : Identifiable> {
    fun remove(obj: T)
}