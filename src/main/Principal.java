package main;

import calculos.Calculo;
import conversion.Consulta;
import conversion.Tasa;

import java.util.Scanner;

public class Principal {

        public static void main(String[] args) {
            String monedaBase;
            String monedaObjetivo;
            int seleccion = 0;
            int valorConvertir;
            String menu = """
            \n
            ************************************************
            Bienvenido/a al Conversor de Moneda
  
            ***********************
            Opciones de conversión:
            ***********************
            1) Dólar =>> Peso argentino
            2) Peso argentino =>> Dólar
            3) Dólar =>> Real brasileño
            4) Real brasileño =>> Dólar
            5) Dólar =>> Peso colombiano
            6) Peso colombiano =>> Dólar
            7) Salir
            Elija una opción válida:
            ************************************************
            """;

            Scanner lectura = new Scanner(System.in);
            Consulta conversion = new Consulta();
            Calculo calculo = new Calculo();

            do {
                //Solicitar y validar OPCION
                System.out.println(menu);
                try {
                    seleccion = lectura.nextInt();
                } catch(Exception e){
                    System.out.println("Entrada no válida. Por favor, ingrese solo caracteres numéricos.");
                    lectura.next();  // Limpiar el buffer de entrada
                    continue;  // Repetir el ciclo
                }

                if (seleccion == 7){
                    System.out.println("Saliendo del programa...");
                    break;
                }

                switch (seleccion){
                    case 1:
                        monedaBase = "USD";
                        monedaObjetivo = "ARS";
                        break;
                    case 2:
                        monedaBase = "ARS";
                        monedaObjetivo = "USD";
                        break;
                    case 3:
                        monedaBase = "USD";
                        monedaObjetivo = "BRL";
                        break;
                    case 4:
                        monedaBase = "BRL";
                        monedaObjetivo = "USD";
                        break;
                    case 5:
                        monedaBase = "USD";
                        monedaObjetivo = "COP";
                        break;
                    case 6:
                        monedaBase = "COP";
                        monedaObjetivo = "USD";
                        break;
                    default:
                        System.out.println("Opción no válida, por favor ingrese un número entre 1 y 7.");
                        continue;//Mostrar nuevamente el menu si se ingresa un número no válido
                    }

                //Solicitar y validar el valor a convertir
                System.out.println("Ingresa el valor que deseas convertir");
                try {
                    valorConvertir = lectura.nextInt();
                } catch (Exception e){
                    System.out.println("Entrada no válida. Por favor, ingrese solo números enteros.");
                    lectura.next();  // Limpiar el buffer de entrada
                    continue;
                }

                // Validar que el valor a convertir sea mayor que cero(0)
                if (valorConvertir <= 0) {
                    System.out.println("El valor a convertir debe ser un número mayor que cero(0).");
                    continue; // Reiniciar el bucle mostrando el menú, si no hay un valor válido a convertir
                }

                //Enviar las dos monedas a convertir a la clase Consulta
                try {
                    Tasa tasa = conversion.consultaTasa(monedaBase, monedaObjetivo);
                    if (tasa != null) {
                        System.out.println(calculo.calcular(tasa.base_code(), tasa.target_code(), valorConvertir,
                                tasa.conversion_rate()));
                    }
                }catch (Exception e){
                        System.out.println("No se pudo obtener la tasa de conversión.");
                }

            }while (seleccion != 7);

            System.out.println("Finalizó el programa");
            lectura.close();
        }
}


