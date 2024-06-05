package org.example.Servicio;
import org.example.Modelo.Contacto;
import org.example.Modelo.NodoContacto;

import java.time.LocalDate;
import java.io.*;

public class Agenda implements Serializable {
    private static final long serialVersionUID = 1L;
    private NodoContacto raiz;

    public Agenda() {
        this.raiz = null;
    }

    public void agregarContacto(String nombre, long telefono, String correoElectronico, LocalDate fechaNaciemiento) {
        Contacto nuevoContacto = new Contacto(nombre, telefono, correoElectronico, fechaNaciemiento);
        if (this.raiz == null) {
            this.raiz = new NodoContacto(nuevoContacto);
        } else {
            this.insertar(this.raiz, nuevoContacto);
        }
    }

    private void insertar(NodoContacto padre, Contacto contacto) {
        if (contacto.getNombre().compareTo(padre.getContacto().getNombre()) < 0) {
            if (padre.getIzdo() == null) {
                padre.setIzdo(new NodoContacto(contacto));
            } else {
                insertar(padre.getIzdo(), contacto);
            }
        } else if (contacto.getNombre().compareTo(padre.getContacto().getNombre()) > 0) {
            if (padre.getDcho() == null) {
                padre.setDcho(new NodoContacto(contacto));
            } else {
                insertar(padre.getDcho(), contacto);
            }
        }
    }

    public Contacto buscarContacto(String criterio) {
        return buscar(this.raiz, criterio);
    }

    private Contacto buscar(NodoContacto nodo, String criterio) {
        if (nodo == null) {
            return null;
        }

        Contacto contacto = nodo.getContacto();
        if (criterio.equals(contacto.getNombre()) || criterio.equals(contacto.getCorreoElectronico()) || String.valueOf(contacto.getTelefono()).equals(criterio)) {
            return contacto;
        }

        Contacto result = buscar(nodo.getIzdo(), criterio);
        if (result != null) {
            return result;
        }

        return buscar(nodo.getDcho(), criterio);
    }

    public Contacto buscar(Contacto contacto) {
        return buscar(this.raiz, contacto);
    }

    private Contacto buscar(NodoContacto nodo, Contacto contacto) {
        if (nodo == null) {
            return null;
        }

        if (contacto.equals(nodo.getContacto())) {
            return nodo.getContacto();
        }

        Contacto result = buscar(nodo.getIzdo(), contacto);
        if (result != null) {
            return result;
        }

        return buscar(nodo.getDcho(), contacto);
    }

    public void eliminarContacto(String nombre) {
        this.raiz = eliminar(this.raiz, nombre);
    }

    private NodoContacto eliminar(NodoContacto nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        if (nombre.compareTo(nodo.getContacto().getNombre()) < 0) {
            nodo.setIzdo(eliminar(nodo.getIzdo(), nombre));
        } else if (nombre.compareTo(nodo.getContacto().getNombre()) > 0) {
            nodo.setDcho(eliminar(nodo.getDcho(), nombre));
        } else {
            if (nodo.getIzdo() == null) {
                return nodo.getDcho();
            } else if (nodo.getDcho() == null) {
                return nodo.getIzdo();
            }

            NodoContacto temp = minValorNodo(nodo.getDcho());
            nodo.getContacto().setTelefono(temp.getContacto().getTelefono());
            nodo.getContacto().setNombre(temp.getContacto().getNombre());
            nodo.setDcho(eliminar(nodo.getDcho(), temp.getContacto().getNombre()));
        }
        return nodo;
    }

    private NodoContacto minValorNodo(NodoContacto nodo) {
        NodoContacto actual = nodo;
        while (actual.getIzdo() != null) {
            actual = actual.getIzdo();
        }
        return actual;
    }

    public void mostrarContactos() {
        inOrden(this.raiz);
    }

    private void inOrden(NodoContacto nodo) {
        if (nodo != null) {
            inOrden(nodo.getIzdo());
            System.out.println("Nombre: " + nodo.getContacto().getNombre() + ", Teléfono: " + nodo.getContacto().getTelefono());
            inOrden(nodo.getDcho());
        }
    }

    public void serializar(String archivo) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            out.writeObject(this);
        }
    }

    public static Agenda deserializar(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            return (Agenda) in.readObject();
        }
    }
}



