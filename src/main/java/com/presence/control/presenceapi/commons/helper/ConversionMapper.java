package com.presence.control.presenceapi.commons.helper;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public final class ConversionMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    private ConversionMapper(){
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }


}
