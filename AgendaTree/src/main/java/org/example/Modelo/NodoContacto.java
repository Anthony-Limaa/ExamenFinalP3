package org.example.Modelo;

import java.io.Serializable;

public class NodoContacto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Contacto contacto;
    private NodoContacto izdo;
    private NodoContacto dcho;

    public NodoContacto(Contacto contacto) {
        this.contacto = contacto;
        this.izdo = null;
        this.dcho = null;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public NodoContacto getIzdo() {
        return izdo;
    }

    public void setIzdo(NodoContacto izdo) {
        this.izdo = izdo;
    }

    public NodoContacto getDcho() {
        return dcho;
    }

    public void setDcho(NodoContacto dcho) {
        this.dcho = dcho;
    }
}

