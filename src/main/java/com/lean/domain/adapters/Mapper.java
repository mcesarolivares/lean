package com.lean.domain.adapters;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lean.domain.exception.ControlException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapea los objetos con los mismos nombres de atributos.
 * 
 * @author <b>Developer</b>: Cesar Olivares<br />
 *         <b>Cambios</b>:<br />
 *         <ul>
 *            <li>25-09-2021 - Creaci&oacute;n del proyecto</li>
 *         </ul>
 * @version 1.0
 *
 */
public class Mapper {

    private Mapper() {
        throw new IllegalStateException("Mapper class");
    }

    /**
     * Convierte un objeto origen a un destino con campos con el mismo nombre.
     * 
     * @param destino la clase destino a convertir
     * @param origen  objeto origen a transformar
     * @return el objeto de destino de la clase <code>destino</code>
     */
    public static <D> D mapper(Class<D> destino, Object origen) {
        try {
            if (origen == null) {
                return null;
            }
            Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {

                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getAnnotation(SkipSerialize.class) != null;
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            }).create();
            return gson.fromJson(gson.toJson(origen), destino);
        } catch (Exception e) {
            throw new ControlException(e);
        }
    }

    /**
     * Convierte una lista origen a una lista destino con campos con el mismo
     * nombre.
     * 
     * @param destino la clase destino a convertir
     * @param origen  objeto origen a transformar
     * @return lista de objetos destino de la clase <code>destino</code>
     */
    public static <D, O> List<D> mapper(Class<D> destino, List<O> origen) {
        try {
            if (origen == null) {
                return Collections.emptyList();
            }
            return origen.stream().map(o -> mapper(destino, o)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ControlException(e);
        }
    }
}
