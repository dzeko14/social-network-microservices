package com.github.dzeko14.socialNetwork.provider

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

interface StorageProvider<T : Identifiable> {
    fun save(obj: T)

    fun delete(obj: T)

    fun getAll(): List<T>

    fun update(obj: T)

    fun getById(obj: Identifiable): T

    fun validate(obj: T): Boolean
}