package ru.edu.project.lab.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDto {
    // Геттеры и сеттеры
    private long id;
    private String name;

    // Конструкторы
    public GenreDto() {}

    public GenreDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

}