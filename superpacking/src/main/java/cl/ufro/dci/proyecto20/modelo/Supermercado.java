package cl.ufro.dci.proyecto20.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Table(name="supermercado")
public class Supermercado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSupermer;

    @Column(name="nombre")
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Usuario> empaques;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Sucursal> sucursal;

    public Supermercado() {
    }

    public Supermercado(String nombre) {
        this.nombre = nombre;
    }

    public long getIdSupermer() {
        return idSupermer;
    }

    public void setIdSupermer(int idSupermer) {
        this.idSupermer = idSupermer;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getEmpaques() {
        return empaques;
    }

    public void setEmpaques(List<Usuario> empaques) {
        this.empaques = empaques;
    }

    public List<Sucursal> getSucursal() {
        return sucursal;
    }

    public void setSucursal(List<Sucursal> sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public String toString() {
        return "Supermercado{" +
                "idSupermer=" + idSupermer +
                ", nombre='" + nombre + '\'' +
                ", empaques=" + empaques +
                ", sucursal=" + sucursal +
                '}';
    }
}
