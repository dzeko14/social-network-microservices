package com.github.dzeko14.socialNetwork.exceptions

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

class NoSuchObjectInStorageException(
        private val obj: Identifiable
):Exception() {
    override val message: String?
        get() = "There is no object in storage with id: ${obj.id}"
}