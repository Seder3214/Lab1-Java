package ru.edu.project.lab.baseresponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    COMMON_ERROR("Ошибка бизнес-логики", "Повторите запрос позже", "none"),
    NOT_FOUND("Не удалось найти ресурс","По вашему запросу ресурс не найден","none"),
    CLIENT_ERROR("Ошибка в запросе", "Проверьте параметры и повторите запрос","none");

    private final String title;
    private final String text;
    private final String detail;
}
