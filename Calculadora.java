import java.util.Scanner;
import java.util.InputMismatchException;

public class Calculadora {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Seleccione la operación a realizar:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Multiplicación");
            System.out.println("4. División");
            System.out.println("5. Raiz Cuadrada");
            System.out.println("0. Salir");

            System.out.println("Opción: ");

            int opcion = entrada.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("****** OPERACIÓN CERRAR ******");
                    continuar = false;
                    System.out.println("Cerrando calculadora...");
                    System.out.println("****** FIN OPERACIÓN CERRAR ******");
                    break;
                case 1:
                    System.out.println("****** OPERACIÓN SUMA ******");
                    realizarSuma(entrada);
                    System.out.println("******** FIN SUMA ********");
                    break;
                case 2:
                    System.out.println("****** OPERACIÓN RESTA ******");
                    realizarResta(entrada);
                    System.out.println("******** FIN RESTA ********");
                    break;
                case 3:
                    System.out.println("****** OPERACIÓN MULTIPLICACIÓN ******");
                    realizarMultiplicacion(entrada);
                    System.out.println("******** FIN MULTIPLICACIÓN ********");
                    break;
                case 4:
                    System.out.println("****** OPERACIÓN DIVISIÓN ******");
                    realizarDivision(entrada);
                    System.out.println("******** FIN DIVISIÓN ********");
                    break;
                case 5:
                    System.out.println("****** RAIZ CUADRADA ******");
                    realizarRaizCuadrada(entrada);
                    System.out.println("******** FIN RAIZ CUADRADA ********");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo");
            }

            System.out.println();
        }

        entrada.close();
    }

    public static void realizarRaizCuadrada (Scanner entrada) {
        try {
            System.out.println("Digite el numero a calcular: ");
            double numero = entrada.nextDouble();
            if (numero < 0) {
                System.out.println("No se puede obtener raíz de un número negativo");
            } else {
                System.out.println("Resultado: ".concat(String.valueOf(Math.sqrt(numero))));
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Ingrese por favor números.");
            entrada.nextLine();
        }
    }

    public static void realizarDivision(Scanner entrada) {
        try {
            System.out.println("Digite el primer numero: ");
            double num1 = entrada.nextDouble();
            System.out.println("Digite el segundo numero: ");
            double num2 = entrada.nextDouble();
            if (num2 == 0) {
                System.out.println("La división entre cero no está definida");
            } else {
                System.out.println("Resultado: ".concat(String.valueOf(num1 / num2)));
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Ingrese por favor números.");
            entrada.nextLine();
        }
    }

    public static void realizarMultiplicacion(Scanner entrada) {
        boolean ingresarOtro = true;
        double resultado = 1;
        int cantidadDeNumerosIngresados = 0;
        try {
            while (ingresarOtro) {
                System.out.println("Ingresar número ó cero(0) para realizar la multiplicación: ");
                double numero = entrada.nextDouble();
                if (numero == 0) {
                    if (cantidadDeNumerosIngresados == 0) {
                        System.out.println("Ingrese al menos un número diferente de cero");
                    } else {
                        ingresarOtro = false;
                    }
                } else {
                    resultado *= numero;
                }
                cantidadDeNumerosIngresados++;
            }

            System.out.println("Resultado: ".concat(String.valueOf(resultado)));
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Ingrese por favor números.");
            entrada.nextLine();
        }
    }

    public static void realizarResta(Scanner entrada) {
        try {
            System.out.println("Introduzca el primer numero: ");
            int num1 = entrada.nextInt();
            System.out.println("Introduzca el segundo numero: ");
            int num2 = entrada.nextInt();
            System.out.println("Resultado: ".concat(String.valueOf(num1 - num2)));
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Ingrese por favor números.");
            entrada.nextLine();
        }
    }

    public static void realizarSuma(Scanner entrada) {
        boolean ingresarOtro = true;
        double resultado = 0;
        try {
            while (ingresarOtro) {
                System.out.println("Ingresar número ó cero(0) para realizar la suma: ");
                double numero = entrada.nextDouble();
                if (numero == 0) {
                    ingresarOtro = false;
                } else {
                    resultado += numero;
                }
            }

            System.out.println("Resultado: ".concat(String.valueOf(resultado)));
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Ingrese por favor números.");
            entrada.nextLine();
        }
    }
}
