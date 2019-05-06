package com.github.dzeko14.socialNetwork.provider

interface PagedListProvider<T> {
    fun getAllByPage(page: Int): List<T>
}