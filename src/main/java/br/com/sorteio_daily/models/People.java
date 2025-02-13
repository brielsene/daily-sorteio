package br.com.sorteio_daily.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;



public class People {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("cargo")
    private String cargo;
    @JsonProperty("status")
    private String status;

    public People() {
    }

    public People(Integer id, String name, String cargo, String status) {
        this.id = id;
        this.name = name;
        this.cargo = cargo;
        this.status = status;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getCargo() {
        return cargo;
    }

    public String getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cargo='" + cargo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
