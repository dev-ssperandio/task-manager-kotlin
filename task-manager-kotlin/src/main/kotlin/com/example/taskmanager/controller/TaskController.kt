package com.example.taskmanager.controller

import com.example.taskmanager.model.Task
import com.example.taskmanager.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService) {

    @GetMapping
    fun getAllTasks(): List<Task> = taskService.findAll()

    @GetMapping("/{id}")
    fun  getTaskById(@PathVariable id: Long): ResponseEntity<Task> {
        val task = taskService.findById(id)
        return if (task != null) ResponseEntity.ok(task) else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createTask(@RequestBody task: Task) = taskService.save(task)

    @PutMapping("{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody task: Task): ResponseEntity<Task> {
        val updatedTask = taskService.save(task.copy(id = id))
        return ResponseEntity.ok(updatedTask)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTask(@PathVariable id: Long) = taskService.deleteById(id)
}