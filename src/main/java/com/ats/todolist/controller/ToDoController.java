package com.ats.todolist.controller;

import com.ats.todolist.dto.ToDoDto;
import com.ats.todolist.service.ToDoService;
import com.ats.todolist.utils.ApiResponse;
import com.ats.todolist.utils.DefaultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/toDos")
@Tag(name = "To Do List Controller", description = "Controller for managing to do list operations")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Operation(summary = "Create ToDo task",description = "This endpoint creates a new ToDo task in the list")
    @PostMapping("/create")
    public ApiResponse<ToDoDto> createToDoList(@Validated @RequestBody ToDoDto toDoDto){
        ToDoDto savedToDo = toDoService.createToDo(toDoDto);
        return DefaultResponse.displayCreatedObject(savedToDo);
    }

    @Operation(summary = "Get ToDo task by Id", description = "This endpoint retrieves a ToDo task by its ID")
    @GetMapping("/getById/{id}")
    public ApiResponse<ToDoDto> getToDoById(@PathVariable("id") Long id) {
        ToDoDto toDoDto = toDoService.getToDoById(id);
        return DefaultResponse.displayFoundObject(toDoDto);
    }

    @Operation(summary = "Update ToDo task", description = "This endpoint update a ToDo task by its ID")
    @PutMapping("/update/{id}")
    public ApiResponse<ToDoDto> updateToDo(@Validated @RequestBody ToDoDto toDoDto, @PathVariable("id") Long id){
        ToDoDto updateToDO = toDoService.updateToDo(toDoDto, id);
        return DefaultResponse.displayUpdatedObject(updateToDO);
    }

    @Operation(summary = "Soft Delete task", description = "This endpoint soft delete a ToDo task by its ID")
    @DeleteMapping("/soft-delete/{id}")
    public ApiResponse<ToDoDto> softDeleteToDo(@PathVariable("id") Long id){
        ToDoDto softDeleteToDo = toDoService.softDeleteToDo(id);
        return DefaultResponse.displayUpdatedObject(softDeleteToDo);
    }

    @Operation(summary = "Complete ToDo task", description = "This endpoint marks a ToDo task as completed by its ID")
    @PatchMapping("complete/{id}")
    public ApiResponse<ToDoDto> completeToDo(@PathVariable("id") Long id){
        ToDoDto completeToDo = toDoService.completeToDo(id);
        return DefaultResponse.displayUpdatedObject(completeToDo);
    }
}
