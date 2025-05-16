package ru.edu.project.lab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.edu.project.lab.baseresponse.BaseResponseService;
import ru.edu.project.lab.baseresponse.ResponseWrapper;
import ru.edu.project.lab.service.ComposerService;
import ru.edu.project.lab.dto.ComposerDto;

import java.util.List;
@Validated
@RestController
@RequestMapping("/composers")
@RequiredArgsConstructor
@Tag(name="Композиторы", description = "Операции над композиторами")
public class ComposerController {
    private final ComposerService composerService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех композиторов",description = "Позволяет выгрузить всех композиторов из БД"
    )
    @GetMapping
    public ResponseWrapper<List<ComposerDto>> findAll() {
        return baseResponseService.wrapSuccessResponse(composerService.findAllComposers());
    }
    @Operation(
            summary = "Получение композитора из ID", description = "Позволяет выгрузить одного композитора из БД"
    )
    @GetMapping("/composer/{id}")
    public ResponseWrapper<ComposerDto> getById(@PathVariable @Min(0) Long id) {
        return baseResponseService.wrapSuccessResponse(composerService.findComposerById(id));
    }
    @Operation(
            summary="Создать композитора", description = "Позволяет создать новую запись о композиторе в БД"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createComposer(@RequestBody @Valid ComposerDto composer) {
        composerService.saveComposer(composer);
    }

    @Operation(
            summary = "Обновить данные о композиторе", description = "Позволяет обновить информацию о композиторе в БД"
    )
    @PutMapping("/composer/")
    public void updateComposer(@RequestBody @Valid ComposerDto composer) {
        composerService.updateComposer(composer);
    }

    @Operation(
            summary="Удалить композитора по ID", description = "Позволяет удалить композитора по ID из БД"
    )
    @DeleteMapping("/composer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComposer(@PathVariable @Min(0) long id) {
        composerService.deleteComposerById(id);
    }
}

