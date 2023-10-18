package co.micia.projects.restapi.pricelist.dalbusiness.exceptions;

/**
 * @author José V Niño R
 * Excepcion personalizada para cuando no se encuentra una entidad en la 
 * Base de datos
 * @version 1.0
 * @since 2023
 */

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
