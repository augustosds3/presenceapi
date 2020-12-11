package com.presence.control.presenceapi.application.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Response<T> {

    private T payload;
}
