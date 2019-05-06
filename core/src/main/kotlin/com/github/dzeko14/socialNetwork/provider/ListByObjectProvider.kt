package com.github.dzeko14.socialNetwork.provider

interface ListByObjectProvider<OT,LT> {
    fun provide(by: OT): List<LT>
}