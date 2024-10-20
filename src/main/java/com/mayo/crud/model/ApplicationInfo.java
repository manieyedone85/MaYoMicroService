package com.mayo.crud.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mayo.crud.exception.ViolationModel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * ApplicationInfo class for session expired.
 *
 * @author manigandan subramani
 */
@Data
@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // Skip null fields
public class ApplicationInfo {
    private final String[] env;
    private final String name;
    private final String version;
}
