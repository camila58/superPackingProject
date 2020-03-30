package cl.ufro.dci.proyecto20.modelo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="BlocNota")
public class BlocNota implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBlocNota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = true)
    private Usuario usuario;

    @Column(name="texto")
    private String texto;

    public BlocNota() {
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "BlocNota{" +
                "texto='" + texto + '\'' +
                '}';
    }
}
