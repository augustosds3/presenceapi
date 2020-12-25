package com.presence.control.presenceapi.commons.helper;

import org.modelmapper.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public final class ConversionMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public ConversionMapper(){

        Provider<LocalDate> localDateProvider = new AbstractProvider<LocalDate>() {
            @Override
            public LocalDate get() {
                return LocalDate.now();
            }
        };

        Provider<LocalTime> localTimeProvider = new AbstractProvider<LocalTime>(){
            @Override
            public LocalTime get() {
                return LocalTime.now();
            }
        };


        Converter<String, LocalDate> toStringDate = new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate localDate = LocalDate.parse(source, format);
                return localDate;
            }
        };

        Converter<String, LocalTime> toStringTime = new AbstractConverter<String, LocalTime>() {
            @Override
            protected LocalTime convert(String source) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime localTime = LocalTime.parse(source, format);
                return localTime;
            }
        };


        modelMapper.createTypeMap(String.class, LocalDate.class);
        modelMapper.addConverter(toStringDate);
        modelMapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);

        modelMapper.createTypeMap(String.class, LocalTime.class);
        modelMapper.addConverter(toStringTime);
        modelMapper.getTypeMap(String.class, LocalTime.class).setProvider(localTimeProvider);
    }

    public <T,U> U map(T source, Class<U> targetClass){
        return modelMapper.map(source, targetClass);
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }


}
