package org.opencodespace.wordle.client.sdk.service

import org.opencodespace.wordle.core.dto.UserDTO
import org.opencodespace.wordle.core.filters.UserFilter

interface UserService {
    fun getUserById(id: Long): UserDTO?

    fun getUserByHandle(handle: String): UserDTO?

    fun getUsers(): List<UserDTO>

    fun getUsers(filter: UserFilter): List<UserDTO>

    fun createUser(userDTO: UserDTO): UserDTO

    fun updateUser(id: Long, userDTO: UserDTO): UserDTO?

    fun deleteUser(id: Long): Boolean
}