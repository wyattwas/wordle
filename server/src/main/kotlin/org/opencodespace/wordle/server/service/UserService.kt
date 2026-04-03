package org.opencodespace.wordle.server.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.opencodespace.wordle.core.dto.UserDTO
import org.opencodespace.wordle.core.filters.UserFilter
import org.opencodespace.wordle.core.services.UserService
import org.opencodespace.wordle.server.entity.UserEntity
import org.opencodespace.wordle.server.mapper.toDTO
import org.opencodespace.wordle.server.mapper.toEntity
import org.opencodespace.wordle.server.repository.UserRepository

@ApplicationScoped
class UserService : UserService {
    @Inject
    private lateinit var userRepository: UserRepository

    override fun getUserById(id: Long): UserDTO? = userRepository.findById(id).toDTO()

    override fun getUserByHandle(handle: String) = userRepository.listAll().find { it.handle == handle }?.toDTO()

    override fun getUsers() = userRepository.listAll().map { it.toDTO() }

    override fun getUsers(filter: UserFilter) =
        userRepository.listAll().filter { filter.name == null || it.name == filter.name }
            .filter { filter.handle == null || it.handle == filter.handle }
            .filter { filter.platform == null || it.platform === filter.platform }
            .filter { filter.score == null || it.score == filter.score }.map { it.toDTO() }

    override fun createUser(userDTO: UserDTO): UserDTO {
        val entity: UserEntity = userDTO.toEntity()
        userRepository.persist(entity)
        return entity.toDTO()
    }

    override fun updateUser(id: Long, userDTO: UserDTO): UserDTO? {
        val entity: UserEntity? = userRepository.findById(id)
        if (entity == null) {
            throw RuntimeException("User not found")
        }

        entity.name = userDTO.name
        entity.handle = userDTO.handle
        entity.platform = userDTO.platform
        entity.score = userDTO.score
        return entity.toDTO()
    }

    override fun deleteUser(id: Long) = userRepository.deleteById(id)
}
