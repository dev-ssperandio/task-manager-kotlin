package com.example.taskmanager.controller

import com.example.taskmanager.model.Task
import com.example.taskmanager.service.TaskService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(TaskController::class)
class TaskControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var taskService: TaskService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `should return all tasks`() {
        val tasks = listOf(Task(id = 1, title = "Task 1", description = "Description 1"))

        Mockito.`when`(taskService.findAll()).thenReturn(tasks)

        mockMvc.perform(get("/tasks"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].title").value("Task 1"))
    }

    @Test
    fun `should return task by id`() {
        val task = Task(id = 1, title = "Task 1", description = "Description 1")

        Mockito.`when`(taskService.findById(1)).thenReturn(task)

        mockMvc.perform(get("/tasks/{id}", 1))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.title").value("Task 1"))
            .andExpect(jsonPath("$.id").value("Description 1"))
    }

    @Test
    fun `should create a new task`() {
        val task = Task(title = "New Task", description = "New Description")

        Mockito.`when`(taskService.save(task)).thenReturn(task.copy(id = 1))

        mockMvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(task)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.title").value("New Task"))
    }

    @Test
    fun `should delete a task`() {
        Mockito.doNothing().`when`(taskService).deleteById(1)

        mockMvc.perform(delete("/tasks/1"))
            .andExpect(status().isNoContent)
    }

}