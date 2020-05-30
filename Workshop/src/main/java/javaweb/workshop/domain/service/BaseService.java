package javaweb.workshop.domain.service;

public abstract class BaseService {

    private String id;

    public BaseService() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
