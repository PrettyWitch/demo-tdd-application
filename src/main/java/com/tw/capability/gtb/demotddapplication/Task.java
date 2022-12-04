package com.tw.capability.gtb.demotddapplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "must not be null")
    private String name;
    @NotNull(message = "must not be null")
    private Boolean completed;

    public Task(String name, Boolean completed) {
        this.name = name;
        this.completed = completed;
    }
}
