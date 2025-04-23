package io.madeformaid.webmvc.jpa.converter;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class EnumSetConverter<T extends Enum<T>> implements AttributeConverter<Set<T>, String> {

    private final Class<T> enumClass;
    private static final String DELIMITER = ",";

    protected EnumSetConverter(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(Set<T> attribute) {
        if (attribute == null || attribute.isEmpty()) return "";
        return attribute.stream()
                .map(Enum::name)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public Set<T> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) return Set.of();
        return Arrays.stream(dbData.split(DELIMITER))
                .map(String::trim)
                .map(value -> Enum.valueOf(enumClass, value))
                .collect(Collectors.toSet());
    }
}
