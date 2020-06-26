package exam.prep.domain.view;

public abstract class BaseView {

    private Long id;

    public BaseView() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
