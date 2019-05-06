package com.github.dzeko14.socialNetwork.interactors.crud

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

interface GetIdentifiableInteractor<T : Identifiable> {
    fun get(id: Identifiable): T
}