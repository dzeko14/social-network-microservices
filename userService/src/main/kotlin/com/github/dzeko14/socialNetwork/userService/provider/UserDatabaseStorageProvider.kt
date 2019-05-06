package com.github.dzeko14.socialNetwork.userService.provider

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import com.github.dzeko14.socialNetwork.userService.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("userStorageProvider")
class UserDatabaseStorageProvider(
        @Autowired private val repo: UserRepository
) : DefaultDatabaseStorageProvider<User, UserImpl> (repo) {
    override fun getObject(obj: User): UserImpl {
        return UserImpl(obj)
    }

    override fun validate(obj: User): Boolean {
        return repo.countById(obj.id) > 0
    }

    override fun updateObject(old: User, new: User): UserImpl {
        return UserImpl(
                id = old.id,
                login = old.login,
                email = if (new.email.isEmpty()) old.email else new.email,
                password = if (new.password.isEmpty()) old.password else new.password,
                name = if (new.name.isEmpty()) old.name else new.name
        )
    }
}