package com.mayo.crud.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Component
@Slf4j
public class Helper {
    Helper() {
    }

    public static UUID generateId() {
        return UUID.randomUUID();
    }

    public static LocalDateTime getLocalUtcDateTime() {
        return LocalDateTime.now(Clock.systemUTC());
    }

    public static ZonedDateTime getUtcZoneDateTime() {
        return ZonedDateTime.now(Clock.systemUTC());
    }

    public static String stringify(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

}
