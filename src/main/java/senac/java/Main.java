package senac.java;
import java.io.IOException;
import senac.java.Services.Servidor;
public class Main {
    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
        servidor.apiServer();
    }
}