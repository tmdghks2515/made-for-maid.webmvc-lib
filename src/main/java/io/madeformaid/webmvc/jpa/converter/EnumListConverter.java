package io.madeformaid.webmvc.jpa.converter;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class EnumListConverter<T extends Enum<T>> implements AttributeConverter<List<T>, String> {

    private final Class<T> enumClass;
    private static final String DELIMITER = ",";

    protected EnumListConverter(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(List<T> attribute) {
        if (attribute == null || attribute.isEmpty()) return "";
        return attribute.stream()
                .map(Enum::name)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public List<T> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) return List.of();
        return Arrays.stream(dbData.split(DELIMITER))
                .map(String::trim)
                .map(value -> Enum.valueOf(enumClass, value))
                .collect(Collectors.toList());
    }
}
