package by.nikita.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AbstractIdAwareDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
