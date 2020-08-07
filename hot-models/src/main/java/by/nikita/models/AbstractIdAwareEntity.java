package by.nikita.models;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractIdAwareEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    public AbstractIdAwareEntity() {
    }

    public AbstractIdAwareEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
