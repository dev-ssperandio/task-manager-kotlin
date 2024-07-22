package com.example.taskmanager.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Task (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long  = 0,
    val title: String,
    val description: String,
    val completed: Boolean = false
)