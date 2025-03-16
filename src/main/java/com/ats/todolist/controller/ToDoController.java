package com.ats.todolist.controller;

import com.ats.todolist.dto.ToDoDto;
import com.ats.todolist.service.ToDoService;
import com.ats.todolist.utils.ApiResponse;
import com.ats.todolist.utils.DefaultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("api/v1/todos")
@Tag(name = "To-Do List Controller", description = "Controller for managing to-do list operations")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Operation(summary = "Create a To-Do task",description = "This endpoint creates a new To-Do task in the list")
    @PostMapping("/create")
    public ApiResponse<ToDoDto> createToDoList(@Validated @RequestBody ToDoDto toDoDto){
        ToDoDto savedToDo = toDoService.createToDo(toDoDto);
        return DefaultResponse.displayCreatedObject(savedToDo);
    }

    @Operation(summary = "Get To-Do task by Id", description = "This endpoint retrieves a To-Do task by its ID")
    @GetMapping("/getById/{id}")
    public ApiResponse<ToDoDto> getToDoById(@PathVariable("id") Long id) {
        ToDoDto toDoDto = toDoService.getToDoById(id);
        return DefaultResponse.displayFoundObject(toDoDto);
    }

    @Operation(summary = "Update To-Do task", description = "This endpoint update a To-Do task by its ID")
    @PutMapping("/update/{id}")
    public ApiResponse<ToDoDto> updateToDo(@Validated @RequestBody ToDoDto toDoDto, @PathVariable("id") Long id){
        ToDoDto updateToDO = toDoService.updateToDo(toDoDto, id);
        return DefaultResponse.displayUpdatedObject(updateToDO);
    }

    @Operation(summary = "Soft Delete task", description = "This endpoint soft delete a To-Do task by its ID")
    @DeleteMapping("/soft-delete/{id}")
    public ApiResponse<ToDoDto> softDeleteToDo(@PathVariable("id") Long id){
        ToDoDto softDeleteToDo = toDoService.softDeleteToDo(id);
        return DefaultResponse.displayUpdatedObject(softDeleteToDo);
    }

    @Operation(summary = "Complete To-Do task", description = "This endpoint marks a To-Do task as completed by its ID")
    @PatchMapping("complete/{id}")
    public ApiResponse<ToDoDto> completeToDo(@PathVariable("id") Long id){
        ToDoDto completeToDo = toDoService.completeToDo(id);
        return DefaultResponse.displayUpdatedObject(completeToDo);
    }

    @Operation(summary = "Search To-Do task with pagination", description = "This endpoint retrieves all to-do with pagination")
    @GetMapping("/search")
    public ApiResponse<Page<ToDoDto>> searchToDos(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String startDate,
        @RequestParam(required = false) String endDate,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "dateCreated") String sortBy,
        @RequestParam(defaultValue = "desc") String sortDirection
    ){
        LocalDateTime startDateTime = (startDate != null) ? LocalDateTime.parse(startDate, DateTimeFormatter.ISO_DATE_TIME) : null;
        LocalDateTime endDateTime = (endDate != null) ? LocalDateTime.parse(endDate, DateTimeFormatter.ISO_DATE_TIME) : null;

        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ToDoDto> searchToDo = toDoService.searchToDos(keyword, startDateTime, endDateTime, pageable);
        return DefaultResponse.displayFoundObject(searchToDo);
    }
}
