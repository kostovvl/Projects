package spring.exam.domain.binding;

public abstract class BaseBinding {

    private Long id;

    public BaseBinding() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
