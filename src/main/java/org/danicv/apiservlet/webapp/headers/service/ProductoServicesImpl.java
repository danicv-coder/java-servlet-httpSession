package org.danicv.apiservlet.webapp.headers.service;

import org.danicv.apiservlet.webapp.headers.models.Producto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductoServicesImpl implements ProductoService {
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "Computacion", 175000),
                new Producto(2L, "mesa de escritorio", "oficina", 8000),
                new Producto(3L, "teclado mecanico", "computacion", 4000));

    }
}
