import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    private String usuario;
    private String clave;
    private double saldo;
    private List<String> historial;

    public Cuenta(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
        this.saldo = 0.0;
        this.historial = new ArrayList<>();
        historial.add("Cuenta creada");
    }

    public String getUsuario() {
        return usuario;
    }

    public boolean verificarClave(String clave) {
        return this.clave.equals(clave);
    }

    public double getSaldo() {
        historial.add("Consulta de saldo: S/ " + saldo);
        return saldo;
    }

    public void depositar(double monto) {
        saldo += monto;
        historial.add("Depósito: S/ " + monto);
    }

    public void retirar(double monto) {
        if (monto <= saldo) {
            saldo -= monto;
            historial.add("Retiro: S/ " + monto);
        } else {
            historial.add("Intento de retiro fallido: S/ " + monto + " (Saldo insuficiente)");
        }
    }

    public void cambiarClave(String nuevaClave) {
        this.clave = nuevaClave;
        historial.add("Clave modificada");
    }

    public List<String> getHistorial() {
        return historial;
    }
}
