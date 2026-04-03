package org.opencodespace.wordle.server.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "guess")
data class GuessEntity constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var date: LocalDate,
    @Column(name = "guess_index")
    var index: Int,
    var word: String,
    var points: Int = 0,
    @Column(name = "correct")
    var isCorrect: Boolean = false,
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserEntity
)
