public class Paciente {
    private String nombre;
    private double peso;
    private int edad;
    private String estado;
    private double factorDosis;
    private int dosisAcumulada;
    private Jaula jaula;

    //Constructor
    public Paciente(String nombre, double peso, int edad, String estado, double factorDosis) {
        setNombre(nombre);
        setPeso(peso);
        setEdad(edad);
        setEstado(estado);
        setFactorDosis(factorDosis);
        this.dosisAcumulada = 0;
        this.jaula = null;
    }

    //Metodo
    public double calcularDosisMaxima() {
        return peso * factorDosis;
    }

    public String getTipo() {
        return "Paciente";
    }

    //Getters
    public String getNombre() { return nombre; }
    public double getPeso() { return peso; }
    public int getEdad() { return edad; }
    public String getEstado() { return estado; }
    public double getFactorDosis() { return factorDosis; }
    public double getDosisAcumulada() { return dosisAcumulada; }
    public Jaula getJaula() { return jaula; }

    //Setters
    public boolean setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) return false;
        this.nombre = nombre.trim();
        return true;
    }

    public boolean setPeso(double peso) {
        double maximo = 80;
        if (this instanceof Gato || this instanceof AveExotica) maximo = 15;
        if (peso <= 0 || peso > maximo) return false;
        this.peso = peso;
        return true;
    }

    public boolean setEdad(int edad) {
        if (edad < 0 || edad > 30) return false;
        this.edad = edad;
        return true;
    }

    public boolean setEstado(String estado) {
        if (estado == null) return false;
        if (!estado.equals("INGRESADO") && !estado.equals("CUARENTENA") && !estado.equals("EN_TRATAMIENTO")) return false;
        this.estado = estado;
        return true;
    }

    public boolean setFactorDosis(double factorDosis) {
        if (factorDosis <= 0) return false;
        this.factorDosis = factorDosis;
        return true;
    }

    public boolean setDosisAcumulada(double dosisAcumulada) {
        if (dosisAcumulada < 0) return false;
        this.dosisAcumulada = dosisAcumulada;
        return true;
    }

    public boolean setJaula(Jaula jaula) {
        this.jaula = jaula;
        return true;
    }

}






