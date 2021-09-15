package com.berroteran.academia.roles;
//Ejemplo RolAdministrador.java
//        En este ejemplo definiremos un rol administrador. En el método enabled() indicamos  en el método initialize() si sera o no activo.
//        Así se crean las demás clases para cada rol.

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 *
 * @authoravbravo
 */
@Named
@RequestScoped
public class RolAdministrador implements Serializable{
    private static final long serialVersionUID = 1L;

    @Inject
    ApplicationMenu applicationMenu;
    /**
     * Creates a new instance of RolAdministrador
     */
    public RolAdministrador() {
    }

    public void enabled() {
        /*
         *barra
         */

        applicationMenu.setMenuBarRegistros(Boolean.TRUE);
        applicationMenu.setMenuBarInventario(Boolean.TRUE);
        applicationMenu.setMenuBarFacturacion(Boolean.TRUE);
        applicationMenu.setMenuBarPlanilla(Boolean.TRUE);
        applicationMenu.setMenuBarAsistencia(Boolean.TRUE);
        applicationMenu.setMenuBarContabilidad(Boolean.TRUE);
        applicationMenu.setMenuBarAdministracion(Boolean.TRUE);
        applicationMenu.setMenuBarOrdenes(Boolean.TRUE);
        applicationMenu.setMenuBarReportes(Boolean.TRUE);
        /*
         *menu
         */

        applicationMenu.getArticulo().initialize(Boolean.TRUE);
        applicationMenu.getBanco().initialize(Boolean.TRUE);
        applicationMenu.getBodega().initialize(Boolean.TRUE);
        applicationMenu.getColor().initialize(Boolean.TRUE);
        applicationMenu.getClientes().initialize(Boolean.TRUE);
        applicationMenu.getConyuge().initialize(Boolean.TRUE);
        applicationMenu.getCorregimiento().initialize(Boolean.TRUE);
        applicationMenu.getDistrito().initialize(Boolean.TRUE);
        applicationMenu.getGanga().initialize(Boolean.TRUE);
        applicationMenu.getGrupoarticulo().initialize(Boolean.TRUE);
        applicationMenu.getInterno().initialize(Boolean.TRUE);

        applicationMenu.getModelo().initialize(Boolean.TRUE);
        applicationMenu.getOrden().initialize(Boolean.TRUE);
        applicationMenu.getPorcentaje().initialize(Boolean.TRUE);
        applicationMenu.getProveedor().initialize(Boolean.TRUE);
        applicationMenu.getProvincia().initialize(Boolean.TRUE);
        applicationMenu.getRol().initialize(Boolean.TRUE);
        applicationMenu.getTamano().initialize(Boolean.TRUE);
        applicationMenu.getTienda().initialize(Boolean.TRUE);
        applicationMenu.getTipoorden().initialize(Boolean.TRUE);
        applicationMenu.getSubtipoorden().initialize(Boolean.TRUE);


        applicationMenu.getUsuario().initialize(Boolean.TRUE);

    }




}