package by.nikita.models;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractIdAwareEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
