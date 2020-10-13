package br.com.p4f.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class CityDTO {

    @NotNull(message = "id city is required")
    private Long id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDTO cityDTO = (CityDTO) o;
        return Objects.equals(id, cityDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
