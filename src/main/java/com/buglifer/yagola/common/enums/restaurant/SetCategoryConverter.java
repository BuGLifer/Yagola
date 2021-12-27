package com.buglifer.yagola.common.enums.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.EnumSet;

@Slf4j
@Converter
public class SetCategoryConverter implements AttributeConverter<EnumSet<Category>, String> {

    @Override
    public String convertToDatabaseColumn(EnumSet<Category> attribute) {
        StringBuilder sb = new StringBuilder();
        attribute.stream().forEach(e -> sb.append(e.name()+","));
        String result = sb.toString();
        if(result.charAt(result.length() - 1) == ',') result = result.substring(0, result.length() - 1);
        return result;
    }

    @Override
    public EnumSet<Category> convertToEntityAttribute(String dbData) {
        if(dbData == null || dbData == "" || dbData.contains(".")) return EnumSet.noneOf(Category.class);
        EnumSet<Category> attribute = EnumSet.noneOf(Category.class);
        String[] dbDataArray = StringUtils.trimAllWhitespace(dbData).toUpperCase().split(",");
        Arrays.stream(dbDataArray).forEach(e -> attribute.add(Category.valueOf(e)));
        return attribute;
    }
}