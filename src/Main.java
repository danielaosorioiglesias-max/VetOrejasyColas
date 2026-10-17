import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hospital hospital = new Hospital();
        boolean continuar = true;

        while (continuar) {
            String linea = scanner.nextLine().trim();
            if (linea.equalsIgnoreCase("salir")) {
                continuar = false;
            } else if (!linea.isEmpty()) {
                String[] partes = linea.split("\\s+");
                String comando = partes[0].toLowerCase();
                String respuesta = "ERROR: comando desconocido.";

                try {
                    if (comando.equals("ingresar") && partes.length >= 2) {
                        String tipo = partes[1].toLowerCase();
                        if (tipo.equals("perro") && partes.length == 7) {
                            boolean vacunado = partes[6].equalsIgnoreCase("si");
                            if (!vacunado && !partes[6].equalsIgnoreCase("no")) respuesta = "ERROR: datos invalidos";
                            else respuesta = hospital.ingresarPerro(partes[2], Double.parseDouble(partes[3]), Integer.parseInt(partes[4]), partes[5], vacunado);
                        } else if (tipo.equals("gato") && partes.length == 5) {
                            respuesta = hospital.ingresarGato(partes[2], Double.parseDouble(partes[3]), Integer.parseInt(partes[4]));
                        } else if (tipo.equals("ave") && partes.length == 6) {
                            respuesta = hospital.ingresarAve(partes[2], Double.parseDouble(partes[3]), Integer.parseInt(partes[4]), Double.parseDouble(partes[5]));
                        } else respuesta = "ERROR: datos invalidos";
                    } else if (comando.equals("vacunar") && partes.length == 2) {
                        respuesta = hospital.vacunar(partes[1]);
                    } else if (comando.equals("asignar") && partes.length == 2) {
                        respuesta = hospital.asignar(partes[1]);
                    } else if (comando.equals("medicar") && partes.length == 3) {
                        respuesta = hospital.medicar(partes[1], Double.parseDouble(partes[2]));
                    } else if (comando.equals("alta") && partes.length == 2) {
                        respuesta = hospital.darAlta(partes[1]);
                    } else if (comando.equals("reporte") && partes.length == 1) {
                        respuesta = hospital.reporte();
                    }
                } catch (NumberFormatException e) {
                    respuesta = "ERROR: datos invalidos";
                }
                System.out.println(respuesta);
            }
        }
        scanner.close();
    }
}
