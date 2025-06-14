import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CajeroGUI extends JFrame {
    private Cuenta cuenta;
    private ArrayList<String> historial = new ArrayList<>();

    private String obtenerFechaHora() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formato);
    }

    public CajeroGUI() {
        setTitle("Simulador de Cajero Automatico");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        JButton crearCuentaBtn = new JButton("Crear cuenta");
        JButton iniciarSesionBtn = new JButton("Iniciar sesion");
        JButton historialBtn = new JButton("Ver historial");
        JButton salirBtn = new JButton("Salir");

        add(crearCuentaBtn);
        add(iniciarSesionBtn);
        add(historialBtn);
        add(salirBtn);

        crearCuentaBtn.addActionListener(e -> crearCuenta());
        iniciarSesionBtn.addActionListener(e -> iniciarSesion());
        historialBtn.addActionListener(e -> verHistorial());
        salirBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void crearCuenta() {
        String usuario = JOptionPane.showInputDialog(this, "Ingrese nombre de usuario:");
        String clave = JOptionPane.showInputDialog(this, "Ingrese clave:");
        if (usuario != null && clave != null) {
            cuenta = new Cuenta(usuario, clave);
            historial.add("Cuenta creada para usuario: " + usuario);
            JOptionPane.showMessageDialog(this, "Cuenta creada con exito.");
        }
    }

    private void iniciarSesion() {
        if (cuenta == null) {
            JOptionPane.showMessageDialog(this, "No hay cuenta creada.");
            return;
        }
        String usuario = JOptionPane.showInputDialog(this, "Usuario:");
        String clave = JOptionPane.showInputDialog(this, "Clave:");

        if (cuenta.getUsuario().equals(usuario) && cuenta.verificarClave(clave)) {
            menuCuenta();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o clave incorrectos.");
        }
    }

    private void menuCuenta() {
        String[] opciones = {"Consultar saldo", "Depositar", "Retirar", "Cambiar clave", "Cerrar sesion"};
        boolean sesion = true;

        while (sesion) {
            int opcion = JOptionPane.showOptionDialog(this, "Seleccione una opcion", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    JOptionPane.showMessageDialog(this, "Saldo: S/ " + cuenta.getSaldo());
                    historial.add(obtenerFechaHora() + " - Consulto saldo.");
                    break;
                case 1:
                    String dep = JOptionPane.showInputDialog(this, "Monto a depositar:");
                    if (dep != null) {
                        try {
                            double monto = Double.parseDouble(dep);
                            cuenta.depositar(monto);
                            historial.add(obtenerFechaHora() + " - Deposito: S/ " + monto);
                            JOptionPane.showMessageDialog(this, "Deposito exitoso de S/ " + monto);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Monto invalido.");
                        }
                    }
                    break;
                case 2:
                    String ret = JOptionPane.showInputDialog(this, "Monto a retirar:");
                    if (ret != null) {
                        try {
                            double monto = Double.parseDouble(ret);
                            cuenta.retirar(monto);
                            historial.add(obtenerFechaHora() + " - Retiro: S/ " + monto);
                            JOptionPane.showMessageDialog(this, "Retiro exitoso de S/ " + monto);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Monto invalido.");
                        }
                    }
                    break;
                case 3:
                    String nuevaClave = JOptionPane.showInputDialog(this, "Nueva clave:");
                    if (nuevaClave != null) {
                        cuenta.cambiarClave(nuevaClave);
                        historial.add(obtenerFechaHora() + " - Cambio la clave.");
                        JOptionPane.showMessageDialog(this, "Clave actualizada.");
                    }
                    break;
                case 4:
                    JOptionPane.showMessageDialog(this, "Sesion cerrada correctamente.");
                    sesion = false;
                    historial.add(obtenerFechaHora() + " - Cerro sesion.");
                    break;
                default:
                    sesion = false;
            }
        }
    }

    private void verHistorial() {
        if (historial.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay historial disponible.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String evento : historial) {
                sb.append("- ").append(evento).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Historial", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CajeroGUI());
    }
}
