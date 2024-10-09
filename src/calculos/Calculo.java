package calculos;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Calculo {
    String monedaBase;
    String monedaObjetivo;
    double valorConvertir;
    double tasaConversion;
    double resultadoConversion;

    public String calcular(String monedaBase, String monedaObjetivo, int valorConvertir ,double tasa){
        this.monedaBase = monedaBase;
        this.monedaObjetivo = monedaObjetivo;
        this.valorConvertir = valorConvertir;
        this.tasaConversion = tasa;
        this.resultadoConversion = this.valorConvertir * this.tasaConversion;

        //Formatear el resultado para mejor legibilidad en el texto de la respuesta
        DecimalFormat decimalFormatParaResultado =  new DecimalFormat("#,###.00");

        // Crear un formateador para la tasa con punto como separador
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);

        /*Convertir la tasa de notación científica a decimal. En algunos casos la api responde con un número
        en notación científica, y para mejorar la legibilidad de la respuesta, se realiza la conversión*/
        DecimalFormat decimalFormatParaTasa = new DecimalFormat("#.##########", symbols);

        return "\n---------------RESULTADO DEL CAMBIO--------------------\n" +
                " Tasa de cambio: " + decimalFormatParaTasa.format(this.tasaConversion) +
                "\n Valor y modenda a cambiar: " + decimalFormatParaResultado.format(this.valorConvertir) +
                " [" + monedaBase + "].  \n Valor y moneda resultado: " +
                decimalFormatParaResultado.format(this.resultadoConversion) +
                " [" + monedaObjetivo + "]." + "\n-------------------------------------------------------" ;
    }
}


