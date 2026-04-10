package org.opencodespace.wordle.client.discord.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.opencodespace.wordle.client.discord.api.UserApiClient
import org.opencodespace.wordle.client.sdk.service.UserService
import org.opencodespace.wordle.core.dto.UserDTO
import org.opencodespace.wordle.core.filters.UserFilter

@ApplicationScoped
class UserServiceImpl : UserService {
    @Inject
    @RestClient
    private lateinit var userApiClient: UserApiClient

    override fun getUserById(id: Long): UserDTO {
        return userApiClient.getUserById(id)
    }

    override fun getUserByHandle(handle: String): UserDTO? {
        val filter = UserFilter()
        filter.handle = handle
        return userApiClient.getUsers(filter).firstOrNull()
    }

    override fun getUsers(): List<UserDTO> {
        return userApiClient.getUsers()
    }

    override fun getUsers(filter: UserFilter): List<UserDTO> {
        return userApiClient.getUsers(filter)
    }

    override fun createUser(userDTO: UserDTO): UserDTO {
        return userApiClient.createUser(userDTO)
    }

    override fun updateUser(id: Long, userDTO: UserDTO): UserDTO? {
        return userApiClient.updateUser(id, userDTO)
    }

    override fun deleteUser(id: Long): Boolean {
        return userApiClient.deleteUser(id)
    }
}