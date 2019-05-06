package com.github.dzeko14.socialNetwork.interactors.crud

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

interface GetAllIdentifiableInteractor<T : Identifiable> {
    fun getAll(): List<T>
}