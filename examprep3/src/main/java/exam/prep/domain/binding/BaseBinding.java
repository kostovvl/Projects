package exam.prep.domain.binding;

public abstract class BaseBinding {

    public Long id;

    public BaseBinding() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
