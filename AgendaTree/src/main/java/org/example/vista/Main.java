package org.example.vista;

import org.example.Modelo.Contacto;
import org.example.Servicio.Agenda;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            // Obtener la ruta de trabajo actual
            String currentDir = System.getProperty("user.dir");
            System.out.println("El archivo se guardará en: " + currentDir);

            Agenda agenda = new Agenda();
            agenda.agregarContacto("Link", 123456789, "link@hyrule.com", LocalDate.of(1990, 1, 1));
            agenda.agregarContacto("Zelda", 987654321, "zelda@hyrule.com", LocalDate.of(1992, 2, 2));
            agenda.agregarContacto("Ganondorf", 555123456, "ganondorf@gerudo.com", LocalDate.of(1993, 2, 2));
            agenda.agregarContacto("Impa", 444987654, "impa@sheikah", LocalDate.of(1994, 2, 5));
            agenda.agregarContacto("Navi", 333456789, "navi@hyrule", LocalDate.of(1998, 2, 2));
            agenda.agregarContacto("Darunia", 222987654, "darunia@goron.com", LocalDate.of(1990, 5, 2));
            agenda.agregarContacto("Ruto", 111123456, "ruto@zora.com", LocalDate.of(1993, 6, 2));
            agenda.agregarContacto("Midna", 666789123, "midna@twilight", LocalDate.of(2006, 2, 2));
            // Serializar la agenda
            String archivo = currentDir + File.separator + "agenda.ser";
            agenda.serializar(archivo);
            System.out.println("Agenda serializada en: " + archivo);

            // Deserializar la agenda
            Agenda agendaCargada = Agenda.deserializar(archivo);
            agendaCargada.mostrarContactos();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}
