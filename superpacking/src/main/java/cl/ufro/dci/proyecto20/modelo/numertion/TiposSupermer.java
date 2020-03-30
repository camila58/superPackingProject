package cl.ufro.dci.proyecto20.modelo.numertion;

public enum TiposSupermer {
    JUMBO("jumbo"),SANTAISABEL("santa isabel"),LIDER("lider"),TOTTUS("tottus"),
    UNIMARC("unimarc");

    private final String tipo;
    TiposSupermer(String tipo){
        this.tipo =tipo;
    }
    public String getTipo(){
        return tipo;
    }
}
