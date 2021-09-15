package com.berroteran.academia.roles;

//ValidadorRoles.java
//        Se usa para validar que rol sera asignado al usuario logeado.

import com.berroteran.academia.util.JSFUtil;
import com.berroteran.academia.util.ResourcesFiles;

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
public class ValidadorRoles implements Serializable{
    private static final long serialVersionUID = 1L;

    @Inject
    ApplicationMenu applicationMenu;
    @Inject
    ResourcesFiles rf;
    @Inject
    RolAdministrador rolAdministrador;
    @Inject
    RolCapturista rolCapturista;
    @Inject
    RolCajera rolCajera;
    @Inject
    RolSuperadministrador rolSuperadministrador;

    public Boolean validarRoles(String rolvalidacion) {

        rolvalidacion = rolvalidacion.toLowerCase();
        Boolean ok = Boolean.TRUE;
        try {
            switch (rolvalidacion) {
                case "administrador":
                    rolAdministrador.enabled();
                    break;
                case "capturista":
                    rolCapturista.enabled();
                    break;
                case "cajera":
                    rolCajera.enabled();
                    break;
                case "superadministrador":
                    rolSuperadministrador.enabled();
                    break;
                default:
                    applicationMenu.enabledAll(false);
                    ok = Boolean.FALSE;
                    JSFUtil.warningDialog(rf.getAppMessage("warning.title"),
                            rf.getAppMessage("info.sinrolasignado"));
            }
        } catch (Exception e) {
            JSFUtil.errorMessage("validarRoles() " + e.getLocalizedMessage());
        }
        return ok;
    }

}