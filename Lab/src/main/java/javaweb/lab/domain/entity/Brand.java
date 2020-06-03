package javaweb.lab.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;
    private Set<Model> models;

    public Brand() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "created")
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Column(name = "modified")
    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @OneToMany(mappedBy = "brand", targetEntity = Model.class,
    fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }
}
