public class Jaula {
    private int numero;
    private String tamano;
    private String zona;
    private Paciente ocupante;

    //Constructor
    public Jaula(int numero, String tamano, String zona) {
        setNumero(numero);
        setTamano(tamano);
        setZona(zona);
        this.ocupante = null;
    }

    //Metodo
    public boolean estaLibre() {
        return ocupante == null;
    }

    //Getters
    public int getNumero() { return numero; }
    public String getTamano() { return tamano; }
    public String getZona() { return zona; }
    public Paciente getOcupante() { return ocupante; }

    //Setters
    public boolean setNumero(int numero) {
        if (numero <= 0) return false;
        this.numero = numero;
        return true;
    }

    public boolean setTamano(String tamano) {
        if (tamano == null || (!tamano.equals("S") && !tamano.equals("M") && !tamano.equals("L"))) return false;
        this.tamano = tamano;
        return true;
    }

    public boolean setZona(String zona) {
        if (zona == null || (!zona.equals("A") && !zona.equals("B"))) return false;
        this.zona = zona;
        return true;
    }

    public boolean setOcupante(Paciente ocupante) {
        this.ocupante = ocupante;
        return true;
    }


}
