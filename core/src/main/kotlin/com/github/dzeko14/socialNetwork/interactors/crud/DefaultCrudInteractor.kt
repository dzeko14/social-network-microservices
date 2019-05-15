package com.github.dzeko14.socialNetwork.interactors.crud

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.exceptions.NoSuchObjectInStorageException
import com.github.dzeko14.socialNetwork.provider.StorageProvider
import java.rmi.NoSuchObjectException

open class DefaultCrudInteractor<T: Identifiable> (
        private val storage: StorageProvider<T>
) : GetIdentifiableInteractor<T>, GetAllIdentifiableInteractor<T>,
        RemoveIdentifiableInteractor<T>, CreateIdentifiableInteractor<T>,
        UpdateIdentifiableInteractor<T> {
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

    override fun remove(obj: T) {
        storage.delete(obj)
    }

    override fun create(obj: T) {
        storage.save(obj)
    }

    override fun update(obj: T) {
        if (!storage.validate(obj)) {
            throw NoSuchObjectException("No such object in database!")
        }

        storage.update(obj)
    }
}