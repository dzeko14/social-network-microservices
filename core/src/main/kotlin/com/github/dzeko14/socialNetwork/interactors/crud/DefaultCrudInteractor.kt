package com.github.dzeko14.socialNetwork.interactors.crud

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.exceptions.NoSuchObjectInStorageException
import com.github.dzeko14.socialNetwork.provider.StorageProvider

open class DefaultCrudInteractor<T: Identifiable> (
        private val storage: StorageProvider<T>
) : GetIdentifiableInteractor<T>, GetAllIdentifiableInteractor<T> {
    override fun get(id: Identifiable): T {
        return try {
            storage.getById(id)
        } catch (e: Exception) {
            throw NoSuchObjectInStorageException(id)
        }
    }

    override fun getAll(): List<T> {
        return storage.getAll()
    }
}