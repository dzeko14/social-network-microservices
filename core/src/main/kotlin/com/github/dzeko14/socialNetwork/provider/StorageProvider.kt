package com.github.dzeko14.socialNetwork.provider

interface StorageProvider<T> {
    fun save(obj: T)

    fun delete(obj: T)

    fun getAll(): List<T>

    fun update(obj: T)

    fun validate(obj: T): Boolean
}