public class AveExotica extends Paciente {
    private double envergadura;

    //Constructor
    public AveExotica(String nombre, double peso, int edad, double envergadura, String estado) {
        super(nombre, peso, edad, estado, 3);
        setEnvergadura(envergadura);
    }

    //Metodos
    @Override
    public String getTipo() { return "Ave"; }

    //Getters
    public double getEnvergadura() { return envergadura; }

    //Setters
    public boolean setEnvergadura(double envergadura) {
        if (envergadura <= 0) return false;
        this.envergadura = envergadura;
        return true;
    }


}
