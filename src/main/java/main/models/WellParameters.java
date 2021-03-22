package main.models;

import java.util.Objects;

public class WellParameters {
    private Long wellId;
    private String parameterName;
    private Double value;

    public Long getWellId() {
        return wellId;
    }

    public void setWellId(Long wellId) {
        this.wellId = wellId;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WellParameters that = (WellParameters) o;
        return Objects.equals(wellId, that.wellId) && Objects.equals(parameterName, that.parameterName) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wellId, parameterName, value);
    }
}
