import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.Scanner;

public class SysMedia {
    private static final Logger LOGGER = Logger.getLogger(SysMedia.class.getName());
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    	SysMedia home = new SysMedia();
        home.calcularMedia();
    }

    public void calcularMedia() {
        try {
            FileHandler fileHandler = new FileHandler("logs/file.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);

            System.out.println("Digite a nota do 1º bimestre (0 à 10): ");
            double nota1 = validarNota(scanner.nextDouble());
            LOGGER.info("Nota 1º bimestre inserida: " + nota1);

            System.out.println("Digite a nota do 2º bimestre (0 à 10): ");
            double nota2 = validarNota(scanner.nextDouble());
            LOGGER.info("Nota 2º bimestre inserida: " + nota2);

            double media = (nota1 + nota2) / 2;
            LOGGER.info("Média calculada: " + media);

            System.out.println("Média final: " + media);
            if (media >= 6) {
                System.out.println("Aluno APROVADO!");
                LOGGER.info("Aluno aprovado com média " + media);
            } else {
                System.out.println("Aluno REPROVADO!");
                LOGGER.warning("Aluno reprovado com média " + media);
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao configurar log em arquivo: ", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao processar notas: ", e);
        }
    }

    private double validarNota(double nota) {
        if (nota < 0 || nota > 10) {
            LOGGER.warning("Nota inválida inserida: " + nota);
            throw new IllegalArgumentException("A nota deve ser entre 0 e 10");
        }
        return nota;
    }
}
