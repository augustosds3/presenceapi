package com.presence.control.presenceapi.commons.mapper;

import org.modelmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper(){

        ModelMapper modelmapper = new ModelMapper();


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

        modelmapper.createTypeMap(String.class, LocalDate.class);
        modelmapper.addConverter(toStringDate);
        modelmapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);

        modelmapper.createTypeMap(String.class, LocalTime.class);
        modelmapper.addConverter(toStringTime);
        modelmapper.getTypeMap(String.class, LocalTime.class).setProvider(localTimeProvider);

        return modelmapper;
    }

}
