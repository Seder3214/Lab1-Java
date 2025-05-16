package ru.edu.project.lab.entities;

import org.springframework.context.annotation.Bean;
import ru.edu.project.lab.dto.GenreDto;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Collection;
public class MusicGenreCache {
    private final ConcurrentHashMap<Long, GenreDto> cacheById;
    private final ConcurrentHashMap<String, Long> idByName;
    private final AtomicLong idGenerator;

    public MusicGenreCache() {
        this.cacheById = new ConcurrentHashMap<>();
        this.idByName = new ConcurrentHashMap<>();
        this.idGenerator = new AtomicLong(1);
    }

    public GenreDto addGenre(String name) {
        Long id = idByName.computeIfAbsent(name.toLowerCase(), k -> {
            long newId = idGenerator.getAndIncrement();
            GenreDto genre = new GenreDto(newId, name);
            cacheById.put(newId, genre);
            return newId;
        });
        return cacheById.get(id);
    }

    public GenreDto getGenreById(long id) {
        return cacheById.get(id);
    }

    public GenreDto getGenreByName(String name) {
        Long id = idByName.get(name.toLowerCase());
        return id != null ? cacheById.get(id) : null;
    }

    public Collection<GenreDto> getAllGenres() {
        return cacheById.values();
    }

    public boolean containsGenre(String name) {
        return idByName.containsKey(name.toLowerCase());
    }

    public GenreDto removeGenre(String name) {
        Long id = idByName.remove(name.toLowerCase());
        return id != null ? cacheById.remove(id) : null;
    }

    public void clearCache() {
        cacheById.clear();
        idByName.clear();
    }

    public int size() {
        return cacheById.size();
    }
}