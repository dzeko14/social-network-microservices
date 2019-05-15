package com.github.dzeko14.socialNetwork.interactors.crud

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

interface CreateIdentifiableInteractor<T : Identifiable>  {
    fun create(obj: T): T
}