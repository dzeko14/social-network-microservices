package com.github.dzeko14.socialNetwork.interactors.crud

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

interface UpdateIdentifiableInteractor<T : Identifiable>  {
    fun update(obj: T)
}