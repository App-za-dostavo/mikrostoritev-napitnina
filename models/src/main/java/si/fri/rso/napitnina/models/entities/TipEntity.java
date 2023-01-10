package si.fri.rso.napitnina.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "napitnina")
@NamedQueries(value =
        {
                @NamedQuery(name = "TipEntity.getAll", query = "SELECT data FROM TipEntity data"),
                @NamedQuery(name = "TipEntity.getById", query = "SELECT data FROM TipEntity data WHERE data.id=:id")
        })
public class TipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "client")
    private Integer client;

    @Column(name = "order")
    private Integer order;

    @Column(name = "tipValue")
    private Float tipValue;

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

    public void seTipValue(Float tipValue) {
        this.tipValue = tipValue;
    }

    public void setTipValue(Float tipValue) {
        this.tipValue = tipValue;
    }
}