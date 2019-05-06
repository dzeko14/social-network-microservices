package com.github.dzeko14.socialNetwork.exceptions

class EmptyFieldException(
        private val obj: Any
) : Exception() {
    override val message: String?
        get() = "Some property of object ${obj::class.simpleName} has empty value!"
}