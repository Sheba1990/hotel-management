package by.nikita.dto;

public class AbstractIdAwareDto {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
