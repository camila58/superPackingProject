package cl.ufro.dci.proyecto20.modelo;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;

    @Column( name = "nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name="idSupermer", nullable = true)
    private Supermercado supermercado;

    @Column(name="correo")
    private String correo;

    @Column(name = "contra")
    private String contra;

    @OneToMany
    private List<Horario> horario;

    @Column(name="sueldo")
    private long sueldo;

    @OneToMany(fetch= FetchType.EAGER)
    private List<BlocNota> blocnota;


    public Usuario(){

    }

    public Usuario(String nombre, String apellido, Supermercado supermercado, String correo, String contra, Sucursal sucursal, Horario horario,
                   long sueldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.supermercado = supermercado;
        this.correo = correo;
        this.contra = contra;
        this.sueldo=sueldo;

    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Supermercado getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(Supermercado supermercado) {
        this.supermercado = supermercado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public List<Horario> getHorario() {
        return horario;
    }

    public void setHorario(List<Horario> horario) {
        this.horario = horario;
    }

    public long getSueldo() {
        return sueldo;
    }

    public void setSueldo(long sueldo) {
        this.sueldo = sueldo;
    }

    public List<BlocNota> getBlocnota() {
        return blocnota;
    }

    public void setBlocnota(List<BlocNota> blocnota) {
        this.blocnota = blocnota;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", supermercado=" + supermercado +
                ", correo='" + correo + '\'' +
                ", contra='" + contra + '\'' +
                ", horario=" + horario +
                ", sueldo=" + sueldo +
                ", blocnota=" + blocnota +
                '}';
    }

    public void addHorario(Horario horario){
        this.horario.add(horario);
    }
    public void removeHorario(Horario horario){
        this.horario.remove(horario);
    }

}
