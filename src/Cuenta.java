public class Cuenta {
    private String usuario;
    private String clave;
    private double saldo;

    public Cuenta(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
        this.saldo = 0.0;
    }

    public String getUsuario() {
        return usuario;
    }

    public boolean verificarClave(String clave) {
        return this.clave.equals(clave);
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.println("Depósito exitoso.");
        } else {
            System.out.println("[X] Monto inválido.");
        }
    }

    public void retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            System.out.println("Retiro exitoso.");
        } else {
            System.out.println("[X] Monto inválido o insuficiente.");
        }
    }

    public void cambiarClave(String nuevaClave) {
        this.clave = nuevaClave;
    }
}
