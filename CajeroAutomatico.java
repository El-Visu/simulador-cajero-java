import java.util.Scanner;

public class CajeroAutomatico {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cuenta cuenta = null;

        while (true) {
            System.out.println("\n--- Bienvenido al Simulador de Cajero Automático ---");
            System.out.println("1. Crear cuenta");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[X] Entrada inválida. Intente de nuevo.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre de usuario: ");
                    String nuevoUsuario = scanner.nextLine();
                    System.out.print("Ingrese clave: ");
                    String nuevaClave = scanner.nextLine();
                    cuenta = new Cuenta(nuevoUsuario, nuevaClave);
                    System.out.println("Cuenta creada con éxito.");
                    break;

                case 2:
                    if (cuenta == null) {
                        System.out.println("[X] No hay cuentas registradas. Crea una primero.");
                        break;
                    }

                    System.out.print("Usuario: ");
                    String usuario = scanner.nextLine();
                    System.out.print("Clave: ");
                    String clave = scanner.nextLine();

                    if (cuenta.getUsuario().equals(usuario) && cuenta.verificarClave(clave)) {
                        boolean sesionActiva = true;
                        while (sesionActiva) {
                            System.out.println("\n--- Menú Principal ---");
                            System.out.println("1. Consultar saldo");
                            System.out.println("2. Depositar dinero");
                            System.out.println("3. Retirar dinero");
                            System.out.println("4. Cambiar clave");
                            System.out.println("5. Cerrar sesión");
                            System.out.print("Seleccione una opción: ");

                            int accion;
                            try {
                                accion = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("[X] Entrada inválida. Intente de nuevo.");
                                continue;
                            }

                            switch (accion) {
                                case 1:
                                    System.out.println("Saldo actual: S/ " + cuenta.getSaldo());
                                    break;
                                case 2:
                                    System.out.print("Ingrese monto a depositar: ");
                                    double montoDep = Double.parseDouble(scanner.nextLine());
                                    cuenta.depositar(montoDep);
                                    break;
                                case 3:
                                    System.out.print("Ingrese monto a retirar: ");
                                    double montoRet = Double.parseDouble(scanner.nextLine());
                                    cuenta.retirar(montoRet);
                                    break;
                                case 4:
                                    System.out.print("Ingrese nueva clave: ");
                                    String nueva = scanner.nextLine();
                                    cuenta.cambiarClave(nueva);
                                    System.out.println("Clave actualizada correctamente.");
                                    break;
                                case 5:
                                    sesionActiva = false;
                                    System.out.println("Sesión cerrada.");
                                    break;
                                default:
                                    System.out.println("[X] Opción no válida.");
                            }
                        }
                    } else {
                        System.out.println("[X] Usuario o clave incorrectos.");
                    }
                    break;

                case 3:
                    System.out.println("Gracias por usar el simulador. Hasta luego.");
                    return;

                default:
                    System.out.println("[X] Opción inválida.");
            }
        }
    }
}
