package br.com.p4f.dto;

import javax.validation.constraints.NotNull;

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
}
