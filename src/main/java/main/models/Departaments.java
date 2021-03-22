package main.models;


import java.util.Objects;

public class Departaments {

    private String nameDepartament;
    private Double radius;
    private Double xO, yO;


    public String getNameDepartament() {
        return nameDepartament;
    }

    public void setNameDepartament(String nameDepartament) {
        this.nameDepartament = nameDepartament;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getxO() {
        return xO;
    }

    public void setxO(Double xO) {
        this.xO = xO;
    }

    public Double getyO() {
        return yO;
    }

    public void setyO(Double yO) {
        this.yO = yO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departaments that = (Departaments) o;
        return Objects.equals(nameDepartament, that.nameDepartament) && Objects.equals(radius, that.radius) && Objects.equals(xO, that.xO) && Objects.equals(yO, that.yO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameDepartament, radius, xO, yO);
    }
}
