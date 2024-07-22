package com.example.taskmanager.service

import com.example.taskmanager.model.Task
import com.example.taskmanager.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: TaskRepository) {

    fun findAll(): List<Task> = taskRepository.findAll()

    fun findById(id: Long): Task? = taskRepository.findById(id).orElse(null)

    fun save(task: Task): Task = taskRepository.save(task)

    fun deleteById(id: Long) = taskRepository.deleteById(id)
}