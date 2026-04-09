package com.mycompany.ejemploproyecto2.utils;

import java.time.Year;
import java.util.Random;

public class GeneradorCodigo {

    private static final Random random = new Random();

    public static String generarCodigo() {
        int anio = Year.now().getValue(); // Año actual (ej: 2026)

        // Genera un número aleatorio de 5 dígitos (10000 - 99999)
        int numero = 10000 + random.nextInt(90000);

        return anio + String.valueOf(numero);
    }
}