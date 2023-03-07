package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @Column(name = "user_id")
    private long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}
