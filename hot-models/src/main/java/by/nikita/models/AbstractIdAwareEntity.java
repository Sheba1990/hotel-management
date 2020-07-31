package by.nikita.models;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractIdAwareEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public AbstractIdAwareEntity() {
    }

    public AbstractIdAwareEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
