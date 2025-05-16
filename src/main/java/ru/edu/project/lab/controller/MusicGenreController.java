package ru.edu.project.lab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.edu.project.lab.baseresponse.BaseResponseService;
import ru.edu.project.lab.baseresponse.ResponseWrapper;
import ru.edu.project.lab.dto.GenreDto;
import ru.edu.project.lab.service.MusicGenreService;

import java.util.Collection;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
@Tag(name="Жанры", description = "Операции над жанрами в кэше")
public class MusicGenreController {

    private final MusicGenreService genreService;
    private final BaseResponseService responseService;

    @PostMapping
    @Operation(
            summary = "Добвление жанра",description = "Позволяет добавить жанр в кэш"
    )
    public ResponseWrapper<GenreDto> createGenre(@RequestBody String genreName) {
        GenreDto createdGenre = genreService.addGenre(genreName);
        return responseService.wrapSuccessResponse(createdGenre);
    }
    @Operation(
            summary = "Поиск жанра в кэше по ID",description = "Позволяет найти жанр в кэше по ID"
    )
    @GetMapping("/{id}")
    public ResponseWrapper<GenreDto> getGenre(@PathVariable Long id) {
        GenreDto genre = genreService.getGenreById(id);
        return responseService.wrapSuccessResponse(genre);
    }

    @Operation(
            summary = "Удаление жанра из кэша по названию жанра",description = "Позволяет удалить жанр по его названию из кэша "
    )
    @DeleteMapping("/by-name/{name}")
    public ResponseWrapper<Void> deleteGenreByName(@PathVariable String name) {
        genreService.removeGenreByName(name);
        return responseService.wrapSuccessResponse(null);
    }
    @Operation(
            summary = "Удаление жанра из кэша по ID жанра",description = "Позволяет удалить жанр по его ID из кэша "
    )
    @DeleteMapping("/by-id/{id}")
    public ResponseWrapper<Void> deleteGenreById(@PathVariable Long id) {
        genreService.removeGenreById(id);
        return responseService.wrapSuccessResponse(null);
    }
    @GetMapping
    @Operation(
            summary = "Получить все жанры из кэша",description = "Позволяет получить все жанры, сохраненные в кэше "
    )
    public ResponseWrapper<Collection<GenreDto>> getAllGenres() {
        Collection<GenreDto> genres = genreService.getAllGenres();
        return responseService.wrapSuccessResponse(genres);
    }


    @Operation(
            summary = "Удаление всех жанров из кэша",description = "Позволяет очистить кэш жанров "
    )
    @DeleteMapping("/clear")
    public ResponseWrapper<Void> clearGenres() {
        genreService.clearCache();
        return responseService.wrapSuccessResponse(null);
    }
}