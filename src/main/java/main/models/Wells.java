package main.models;

import java.util.Objects;

public class Wells {
    private Long id;
    private String name;
    private Double x, y;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wells wells1 = (Wells) o;
        return Objects.equals(id, wells1.id) && Objects.equals(name, wells1.name) && Objects.equals(x, wells1.x) && Objects.equals(y, wells1.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, x, y);
    }
}

