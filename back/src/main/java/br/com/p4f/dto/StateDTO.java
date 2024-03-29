package br.com.p4f.dto;


import java.util.Objects;

public class StateDTO {

    private Long id;
    private String name;
    private String country;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateDTO stateDTO = (StateDTO) o;
        return Objects.equals(id, stateDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
