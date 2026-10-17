import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        Hospital hospital = new Hospital();
        boolean continuar = true;

        System.out.println("========================================");
        System.out.println("    SISTEMA DE HOSPITAL VETERINARIO");
        System.out.println("========================================");

        while (continuar) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Ingresar un perro");
            System.out.println("2. Ingresar un gato");
            System.out.println("3. Ingresar un ave");
            System.out.println("4. Vacunar");
            System.out.println("5. Asignar (jaula)");
            System.out.println("6. Medicar");
            System.out.println("7. Alta");
            System.out.println("8. Reporte");
            System.out.println("9. Salir");
            System.out.print("\n>> Elija una opción (1-9): ");

            String opcionStr = leer.nextLine().trim();
            String respuesta = "";

            try {
                int opcion = Integer.parseInt(opcionStr);

                switch (opcion) {
                    case 1:
                        System.out.print("Nombre: "); String nombreP = leer.nextLine().trim();
                        System.out.print("Peso (kg): "); double pesoP = Double.parseDouble(leer.nextLine().trim());
                        System.out.print("Edad: "); int edadP = Integer.parseInt(leer.nextLine().trim());
                        System.out.print("Raza: "); String raza = leer.nextLine().trim();
                        System.out.print("¿Vacunado? (si/no): "); boolean vacunado = leer.nextLine().trim().equalsIgnoreCase("si");
                        respuesta = hospital.ingresarPerro(nombreP, pesoP, edadP, raza, vacunado);
                        break;
                    case 2:
                        System.out.print("Nombre: "); String nombreG = leer.nextLine().trim();
                        System.out.print("Peso (kg): "); double pesoG = Double.parseDouble(leer.nextLine().trim());
                        System.out.print("Edad: "); int edadG = Integer.parseInt(leer.nextLine().trim());
                        respuesta = hospital.ingresarGato(nombreG, pesoG, edadG);
                        break;
                    case 3:
                        System.out.print("Nombre: "); String nombreA = leer.nextLine().trim();
                        System.out.print("Peso (kg): "); double pesoA = Double.parseDouble(leer.nextLine().trim());
                        System.out.print("Edad: "); int edadA = Integer.parseInt(leer.nextLine().trim());
                        System.out.print("Envergadura (cm): "); double envergadura = Double.parseDouble(leer.nextLine().trim());
                        respuesta = hospital.ingresarAve(nombreA, pesoA, edadA, envergadura);
                        break;
                    case 4:
                        System.out.print("Nombre del paciente a vacunar: ");
                        respuesta = hospital.vacunar(leer.nextLine().trim());
                        break;
                    case 5:
                        System.out.print("Nombre del paciente a asignar jaula: ");
                        respuesta = hospital.asignar(leer.nextLine().trim());
                        break;
                    case 6:
                        System.out.print("Nombre del paciente a medicar: "); String nombreM = leer.nextLine().trim();
                        System.out.print("Dosis (mg): "); double mg = Double.parseDouble(leer.nextLine().trim());
                        respuesta = hospital.medicar(nombreM, mg);
                        break;
                    case 7:
                        System.out.print("Nombre del paciente a dar de alta: ");
                        respuesta = hospital.darAlta(leer.nextLine().trim());
                        break;
                    case 8:
                        respuesta = hospital.reporte();
                        break;
                    case 9:
                        continuar = false;
                        respuesta = "Saliendo del sistema...";
                        break;
                    default:
                        respuesta = "ERROR: Opción no válida. Elija un número del 1 al 9.";
                        break;
                }
            } catch (NumberFormatException e) {
                // Captura el error si el usuario escribe letras donde van números
                respuesta = "ERROR: Ha ingresado un formato inválido (se esperaba un número).";
            }

            if (!respuesta.isEmpty()) {
                System.out.println("\nRespuesta:\n" + respuesta);
                System.out.println("----------------------------------------");
            }
        }
        leer.close();
    }
}