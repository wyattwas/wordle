package org.opencodespace.wordle.server.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped
import org.opencodespace.wordle.server.entity.UserEntity

@ApplicationScoped
class UserRepository : PanacheRepository<UserEntity>
