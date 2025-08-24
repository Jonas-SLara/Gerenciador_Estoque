package com.csi.tasks.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task{
    private Long id;
    private String name;
    private String description;
    private int priority;
    private UUID uuid;
}