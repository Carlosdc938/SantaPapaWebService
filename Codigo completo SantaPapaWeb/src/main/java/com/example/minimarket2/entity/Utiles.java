package com.example.minimarket2.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Utiles {
    static String obtenerFechaActual() {
        String formato = "yyyy-MM-dd";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        LocalDateTime ahora = LocalDateTime.now();
        return formateador.format(ahora);
    }
    static String obtenerHoraActual() {
        String formato = "HH:mm:ss";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        LocalDateTime ahora = LocalDateTime.now();
        return formateador.format(ahora);
    }
}
