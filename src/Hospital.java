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
        jaulas [2] = new Jaula(3,"M", "A");
        jaulas [3] = new Jaula(4, "L", "A");
        jaulas [4] = new Jaula(5, "S","B");
        jaulas [5] = new Jaula(6, "M","B");
    }

    public void cargarDatosIniciales() {
        pacientes[cantidadPacientes++] = new Perro("Rocky", 30.0, 4, "labrador", true, "INGRESADO");
        pacientes[cantidadPacientes++] = new Gato("Misifu", 4.0, 3, 0, "INGRESADO");
        pacientes[cantidadPacientes++] = new AveExotica("Kiwi", 0.8, 2, 70, "INGRESADO");
    }

    public Paciente buscarPaciente(String nombre) {
        for (int i = 0; i < cantidadPacientes; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) return pacientes[i];
        }
        return null;
    }

    private boolean nombreExiste(String nombre) {
        return buscarPaciente(nombre) != null;
    }

    public String ingresarPerro(String nombre, double peso, int edad, String raza, boolean vacunado) {
        if (nombre == null || nombre.trim().isEmpty() || peso <= 0 || peso > 80 || edad < 0 || edad > 30 || raza == null || raza.trim().isEmpty())
            return "ERROR: datos invalidos";
        if (nombreExiste(nombre)) return "ERROR: nombre duplicado";
        if (cantidadPacientes >= pacientes.length) return "ERROR: hospital lleno";
        String estado = vacunado ? "INGRESADO" : "CUARENTENA";
        pacientes[cantidadPacientes++] = new Perro(nombre, peso, edad, raza, vacunado, estado);
        if (!vacunado) return "OK: " + nombre + " ingresado en CUARENTENA";
        return "OK: " + nombre + " ingresado";
    }

    public String ingresarGato(String nombre, double peso, int edad) {
        if (nombre == null || nombre.trim().isEmpty() || peso <= 0 || peso > 15 || edad < 0 || edad > 30)
            return "ERROR: datos invalidos";
        if (nombreExiste(nombre)) return "ERROR: nombre duplicado";
        if (cantidadPacientes >= pacientes.length) return "ERROR: hospital lleno";
        pacientes[cantidadPacientes++] = new Gato(nombre, peso, edad, 0, "INGRESADO");
        return "OK: " + nombre + " ingresado";
    }

    public String ingresarAve(String nombre, double peso, int edad, double envergadura) {
        if (nombre == null || nombre.trim().isEmpty() || peso <= 0 || peso > 15 || edad < 0 || edad > 30 || envergadura <= 0)
            return "ERROR: datos invalidos";
        if (nombreExiste(nombre)) return "ERROR: nombre duplicado";
        if (cantidadPacientes >= pacientes.length) return "ERROR: hospital lleno";
        pacientes[cantidadPacientes++] = new AveExotica(nombre, peso, edad, envergadura, "INGRESADO");
        return "OK: " + nombre + " ingresado";
    }

    public String vacunar(String nombre) {
        Paciente p = buscarPaciente(nombre);
        if (p == null) return "ERROR: paciente no encontrado";
        if (!(p instanceof Perro)) return "ERROR: solo aplica a perros";
        Perro perro = (Perro) p;
        if (perro.isVacunado()) return "ERROR: ya vacunado";
        perro.setVacunado(true);
        perro.setEstado("INGRESADO");
        return "OK: " + p.getNombre() + " vacunado (pasa a INGRESADO)";
    }

    private String tamanoNecesario(Paciente p) {
        String tamano;
        if (p.getPeso() < 5) tamano = "S";
        else if (p.getPeso() <= 25) tamano = "M";
        else tamano = "L";
        if (p instanceof AveExotica) {
            AveExotica ave = (AveExotica) p;
            if (ave.getEnvergadura() > 60 && tamano.equals("S")) tamano = "M";
        }
        return tamano;
    }

    private boolean tamanoCompatible(String disponible, String necesario) {
        if (necesario.equals("S")) return disponible.equals("S") || disponible.equals("M") || disponible.equals("L");
        if (necesario.equals("M")) return disponible.equals("M") || disponible.equals("L");
        return disponible.equals("L");
    }

    public String asignar(String nombre) {
        Paciente p = buscarPaciente(nombre);
        if (p == null) return "ERROR: paciente no encontrado";
        if (p.getEstado().equals("CUARENTENA")) return "ERROR: paciente en cuarentena";
        if (p.getJaula() != null) return "ERROR: paciente ya tiene jaula";

        String zonaNecesaria = (p instanceof AveExotica) ? "B" : "A";
        String tamanoNecesario = tamanoNecesario(p);
        for (int i = 0; i < jaulas.length; i++) {
            if (jaulas[i].estaLibre() && jaulas[i].getZona().equals(zonaNecesaria)
                    && tamanoCompatible(jaulas[i].getTamano(), tamanoNecesario)) {
                jaulas[i].setOcupante(p);
                p.setJaula(jaulas[i]);
                return "OK: " + p.getNombre() + " asignado a jaula " + jaulas[i].getNumero();
            }
        }
        return "ERROR: no hay jaula compatible";
    }

    public String medicar(String nombre, double mg) {
        Paciente p = buscarPaciente(nombre);
        if (p == null) return "ERROR: paciente no encontrado";
        if (mg <= 0) return "ERROR: datos invalidos";
        if (p.getEstado().equals("CUARENTENA")) return "ERROR: paciente en cuarentena";
        if (p.getJaula() == null) return "ERROR: paciente sin jaula";
        if (p instanceof Gato && ((Gato) p).getNivelEstres() >= 8) return "ERROR: gato con estres alto";
        if (mg > p.calcularDosisMaxima()) return "ERROR: dosis excede el maximo";
        if (p.getDosisAcumulada() + mg > 3 * p.calcularDosisMaxima()) return "ERROR: tope acumulado superado";

        p.setDosisAcumulada(p.getDosisAcumulada() + mg);
        p.setEstado("EN_TRATAMIENTO");
        if (p instanceof Gato) {
            Gato gato = (Gato) p;
            int nuevoEstres = gato.getNivelEstres() + 2;
            if (nuevoEstres > 10) nuevoEstres = 10;
            gato.setNivelEstres(nuevoEstres);
        }
        return "OK: " + p.getNombre() + " medicado con " + mg + " mg";
    }

    public String darAlta(String nombre) {
        int posicion = -1;
        for (int i = 0; i < cantidadPacientes; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                posicion = i;
                break;
            }
        }
        if (posicion == -1) return "ERROR: paciente no encontrado";
        Paciente p = pacientes[posicion];
        if (!p.getEstado().equals("EN_TRATAMIENTO")) return "ERROR: paciente no apto para alta";

        if (p.getJaula() != null) {
            p.getJaula().setOcupante(null);
            p.setJaula(null);
        }
        for (int i = posicion; i < cantidadPacientes - 1; i++) pacientes[i] = pacientes[i + 1];
        pacientes[cantidadPacientes - 1] = null;
        cantidadPacientes--;
        return "OK: " + p.getNombre() + " dado de alta";
    }

    public String reporte() {
        String resultado = "--- REPORTE ---\n";
        resultado += "Pacientes: " + cantidadPacientes + "/10\n";
        for (int i = 0; i < cantidadPacientes; i++) {
            Paciente p = pacientes[i];
            String numeroJaula = "-";
            if (p.getJaula() != null) numeroJaula = String.valueOf(p.getJaula().getNumero());
            resultado += p.getNombre() + " | " + p.getTipo() + " | " + String.format(java.util.Locale.US, "%.1f", p.getPeso())
                    + " kg | " + p.getEstado() + " | jaula: " + numeroJaula + "\n";
        }
        int libres = 0;
        for (int i = 0; i < jaulas.length; i++) if (jaulas[i].estaLibre()) libres++;
        resultado += "Jaulas libres: " + libres;
        return resultado;
    }
}
