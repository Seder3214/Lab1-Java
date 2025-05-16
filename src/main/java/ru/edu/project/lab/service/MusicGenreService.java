package ru.edu.project.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.project.lab.baseresponse.BaseResponseService;
import ru.edu.project.lab.baseresponse.ErrorType;
import ru.edu.project.lab.dto.GenreDto;
import ru.edu.project.lab.exception.PenzGtuException;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class MusicGenreService {
    private final ConcurrentHashMap<Long, GenreDto> cacheById = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> idByName = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final BaseResponseService responseService;

    public GenreDto addGenre(String name) {
        try {
            if (name == null || name.isBlank()) {
                throw new PenzGtuException(ErrorType.CLIENT_ERROR, "Название жанра не может быть пустым");
            }

            Long id = idByName.computeIfAbsent(name.toLowerCase(), k -> {
                long newId = idGenerator.getAndIncrement();
                GenreDto genre = new GenreDto(newId, name);
                cacheById.put(newId, genre);
                return newId;
            });
            return cacheById.get(id);
        } catch (Exception e) {
            throw new PenzGtuException(ErrorType.COMMON_ERROR, "Ошибка при добавлении жанра", e);
        }
    }

    public GenreDto getGenreById(long id) {
        GenreDto genre = cacheById.get(id);
        if (genre == null) {
            throw new PenzGtuException(ErrorType.NOT_FOUND,
                    String.format("Жанр с ID %d не найден", id));
        }
        return genre;
    }

    public GenreDto getGenreByName(String name) {
        Long id = idByName.get(name.toLowerCase());
        if (id == null) {
            throw new PenzGtuException(ErrorType.NOT_FOUND,
                    String.format("Жанр '%s' не найден", name));
        }
        return cacheById.get(id);
    }

    public Collection<GenreDto> getAllGenres() {
        try {
            return cacheById.values();
        } catch (Exception e) {
            throw new PenzGtuException(ErrorType.COMMON_ERROR, "Ошибка при получении списка жанров", e);
        }
    }

    public boolean containsGenre(String name) {
        return idByName.containsKey(name.toLowerCase());
    }

    public void removeGenreByName(String name) {
        // Сначала проверяем существование жанра
        Long id = idByName.get(name.toLowerCase());
        if (id == null) {
            throw new PenzGtuException(ErrorType.NOT_FOUND,
                    String.format("Жанр '%s' не найден", name));
        }

        // Если жанр существует, пытаемся удалить
        try {
            idByName.remove(name.toLowerCase());
            cacheById.remove(id);
        } catch (Exception e) {
            throw new PenzGtuException(ErrorType.COMMON_ERROR,
                    "Ошибка при удалении жанра", e);
        }
    }
    public void removeGenreById(Long id) {
        GenreDto genre = cacheById.get(id);
        if (genre == null) {
            throw new PenzGtuException(ErrorType.NOT_FOUND,
                    String.format("Жанр с ID %d не найден", id));
        }

        try {
            idByName.remove(genre.getName().toLowerCase());
            cacheById.remove(id);
        } catch (Exception e) {
            throw new PenzGtuException(ErrorType.COMMON_ERROR,
                    "Ошибка при удалении жанра", e);
        }
    }
    public void clearCache() {
        try {
            cacheById.clear();
            idByName.clear();
        } catch (Exception e) {
            throw new PenzGtuException(ErrorType.COMMON_ERROR, "Ошибка при очистке кеша", e);
        }
    }

    public int size() {
        return cacheById.size();
    }
}