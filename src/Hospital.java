public class Hospital {
    private Paciente [] pacientes;
    private Jaula [] jaulas;
    private int cantidadPacientes;

    //Constructor
    public Hospital(){
        pacientes = new Paciente[10];
        jaulas = new Jaula[6];
        cantidadPacientes = 0;
        cargarJaulas();
        cargarDatosIniciales();
    }

    //Metodo
    private void cargarJaulas (){
        jaulas [0] = new Jaula(1, "S", "A");
        jaulas [1] = new Jaula(2, "M", "A");
        jaulas [3] = new Jaula(3,"M", "A");
        jaulas [4] = new Jaula(4, "L", "A");
        jaulas [5] = new Jaula(5, "S","B");
        jaulas [6] = new Jaula(6, "M","B");
    }





}
