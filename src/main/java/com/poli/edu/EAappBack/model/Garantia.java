package com.poli.edu.EAappBack.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "garantias")
public class Garantia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date fechaRegistro;

    @NotBlank
    @Size(max = 50)
    private String boutique;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;

    @NotBlank
    @Size(max = 30)
    private String email;

    @NotBlank
    @Size(max = 20)
    private String telefono;

    @NotBlank
    @Size(max = 20)
    private String nroFactura;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "garantia_referencia")
    private Set<Referencia> refencia = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "garantia_causa")
    private Set<Causa> causas = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getBoutique() {
        return boutique;
    }

    public void setBoutique(String boutique) {
        this.boutique = boutique;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    public Set<Referencia> getRefencia() {
        return refencia;
    }

    public void setRefencia(Set<Referencia> refencia) {
        this.refencia = refencia;
    }

    public Set<Causa> getCausas() {
        return causas;
    }

    public void setCausas(Set<Causa> causas) {
        this.causas = causas;
    }

}
