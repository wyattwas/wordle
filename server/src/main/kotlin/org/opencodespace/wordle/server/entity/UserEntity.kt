package org.opencodespace.wordle.server.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.opencodespace.wordle.core.models.Platform

@Entity
@Table(name = "user")
data class UserEntity constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var name: String,
    @Column(unique = true)
    var handle: String,
    var platform: Platform,
    var score: Int = 0,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinTable(
        name = "guess",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "id")]
    )
    var guesses: MutableSet<GuessEntity> = HashSet()
)
