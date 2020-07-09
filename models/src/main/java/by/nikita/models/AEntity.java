package by.nikita.models;

import javax.persistence.*;

@MappedSuperclass
public abstract class AEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public AEntity() {
    }

    public AEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
