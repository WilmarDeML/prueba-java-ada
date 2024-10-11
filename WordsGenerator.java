import java.util.Random;

public class WordsGenerator {
    // Cadena dada en el ejercicio
    static char[] cadena = {
            'w','e','r','f','b','h','j','i','u','y','t','r','e','d','f','g','u','t','r','e','s','d','f','g','y','u','i',
            'o','l','k','m','n','b','v','f','r','e','w','s','x','f','g','y','u','i','k','m','n','b','v','f','r','e','w',
            'w','r','t','y','u','i','o','k','m','n','b','v','d','w','s','x','c','f','g','h','u','i','o','p','l','k','n',
            'b','v','f','d','e','w','a','z','x','c','g','h','u','i','o','p','u','y','t','r','e','w','q','s','d','f','g',
            'k','j','b','v','c','x'
    };

    public static void main(String[] args) {

        Random random = new Random();

        // Cantidad de palabras a generar aleatoria de 1 a 10
        int cantidadDePalabras = random.nextInt(10) + 1;
        System.out.println("Cantidad de palabras a generar: ".concat(String.valueOf(cantidadDePalabras)));

        for (int i = 0; i < cantidadDePalabras; i++) {
            String palabra = generarPalabra(random);
            System.out.println("Palabra generada ".concat(i + 1 + ": ").concat(palabra));
        }
    }

    // Genera palabras de longitud aleatoria desde 1 hasta 10
    public static String generarPalabra(Random random) {
        int longitudDePalabra = random.nextInt(10) + 1;
        StringBuilder palabra = new StringBuilder(longitudDePalabra);

        // Se generan las palabras a partir del arreglo cadena
        for (int i = 0; i < longitudDePalabra; i++) {
            char letra = cadena[random.nextInt(cadena.length)];
            palabra.append(letra);
        }

        return palabra.toString();
    }
}
