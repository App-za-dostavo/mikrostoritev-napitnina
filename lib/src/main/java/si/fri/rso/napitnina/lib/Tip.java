package si.fri.rso.napitnina.lib;

public class Tip {
    private Integer id;
    private Integer client;
    private Integer order;
    Float tipValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Float getTipValue() {
        return tipValue;
    }

    public void setTipValue(Float tipValue) {
        this.tipValue = tipValue;
    }
}
