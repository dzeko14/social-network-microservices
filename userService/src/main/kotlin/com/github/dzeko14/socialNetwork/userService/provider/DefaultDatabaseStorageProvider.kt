package com.github.dzeko14.socialNetwork.userService.provider

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.provider.StorageProvider
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

abstract class DefaultDatabaseStorageProvider<T : Identifiable, ImplT : T>(
        private val repo: JpaRepository<ImplT, Long>
)  : StorageProvider<T> {

    abstract fun getObject(obj: T): ImplT

    abstract fun updateObject(old: T, new: T): ImplT

    override fun save(obj: T) {
        repo.save(getObject(obj))
    }

    override fun delete(obj: T) {
        repo.delete(getObject(obj))
    }

    override fun getAll(): List<T> {
        return repo.findAll()
    }

    override fun update(obj: T) {
        repo.save( updateObject(repo.getOne(obj.id), obj))
    }

    override fun getById(obj: Identifiable): T {
        return repo.findById(obj.id).get()
    }
}