package com.example.taskmanager.repository

import com.example.taskmanager.model.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long>