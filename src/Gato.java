public class Gato extends Paciente {
    private int nivelEstres;

    //Constructor
    public Gato(String nombre, double peso, int edad, int nivelEstres, String estado) {
        super(nombre, peso, edad, estado, 6);
        setNivelEstres(nivelEstres);
    }

    //Metodos
    @Override
    public String getTipo() { return "Gato"; }

    //Getters
    public int getNivelEstres() { return nivelEstres; }

    //Setters
    public boolean setNivelEstres(int nivelEstres) {
        if (nivelEstres < 0 || nivelEstres > 10) return false;
        this.nivelEstres = nivelEstres;
        return true;
    }

}
