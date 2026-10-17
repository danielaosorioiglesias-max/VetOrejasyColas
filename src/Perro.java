public class Perro extends Paciente {
    private String raza;
    private boolean vacunado;

    //Constructor
    public Perro(String nombre, double peso, int edad, String raza, boolean vacunado, String estado) {
        super(nombre, peso, edad, estado, 10);
        setRaza(raza);
        this.vacunado = vacunado;
    }

    //Metodos
    @Override
    public String getTipo() { return "Perro"; }

    //Getters
    public String getRaza() { return raza; }
    public boolean isVacunado() { return vacunado; }

    //Setters
    public boolean setRaza(String raza) {
        if (raza == null || raza.trim().isEmpty()) return false;
        this.raza = raza.trim();
        return true;
    }

    public boolean setVacunado(boolean vacunado) {
        this.vacunado = vacunado;
        return true;
    }


}
