package com.berroteran.academia.controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// <editor-fold defaultstate="collapsed" desc="imports">

import com.berroteran.academia.entity.Rol;
import com.berroteran.academia.entity.Usuario;
import com.berroteran.academia.repository.AccessInfoRepository;
import com.berroteran.academia.repository.RolRepository;
import com.berroteran.academia.repository.UsuarioRepository;
import com.berroteran.academia.roles.ValidadorRoles;
import com.berroteran.academia.util.JSFUtil;
import com.berroteran.academia.util.ResourcesFiles;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;
import java.util.logging.Logger;
// </editor-fold>


/**
 *
 *
 */
@Named
@SessionScoped
public class LoginController  implements Serializable  {
    // <editor-fold defaultstate="collapsed" desc="fields">
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    @Inject
    AccessInfoRepository accessInfoRepository;
    @Inject
    ResourcesFiles rf;
    @Inject
    ValidadorRoles validadorRoles;

    Boolean loggedIn = false;
    private String username;
    private String password;
    private String foto;
    private String id;
    private String key;
    String usernameSelected;
    Boolean recoverSession = false;
    Boolean userwasLoged = false;
    Boolean tokenwassend = false;
    String usernameRecover = "";
    String myemail = "@gmail.com";
    String mytoken = "";
    @Inject
    UsuarioRepository usuarioRepository;

    Usuario usuario = new Usuario();

    @Inject
    RolRepository rolRepository;
    Rol rol = new Rol();


    // <editor-fold defaultstate="collapsed" desc="init">
    @PostConstruct
    public void init() {
        loggedIn = false;
        recoverSession = false;
        userwasLoged = false;
        tokenwassend = false;
    }


    public String doLogin() {
        try {

            tokenwassend = false;
            userwasLoged = false;
            loggedIn = true;
            usuario= new Usuario();
            if (username == null || password == null) {
                JSFUtil.addWarningMessage(rf.getAppMessage("login.usernamenotvalid"));
                return null;
            }
            //usernameRecover = usernameRecoveryOfSession();

            recoverSession = !usernameRecover.equals("");
            if (recoverSession) {
                invalidateCurrentSession();
                RequestContext.getCurrentInstance().execute("PF('sessionDialog').show();");
                return "";
            }
            if (recoverSession && usernameRecover.equals(username)) {
            } else {
                /*
                if (isUserLogged(username)) {
                    userwasLoged = true;
                    JSFUtil.warningMessage(rf.getAppMessage("login.alreadylogged"));
                    return "";
                }
                */
            }
            if (!isUserValid()) {
                //accessInfoRepository.save(accessInfoServices.generateAccessInfo(username, "login",rf.getAppMessage("login.usernameorpasswordnotvalid")));
                //JSFUtil.warningMessage(rf.getAppMessage("login.usernameorpasswordnotvalid"));
                return " ";
            }

            saveUserInSession(username, 2100);
            //accessInfoRepository.save(accessInfoServices.generateAccessInfo(username, "login",rf.getAppMessage("login.welcome") ));
            loggedIn = true;
            foto = "img/me.jpg";

            JSFUtil.successMessage(rf.getAppMessage("login.welcome") + " " + usuario.getNombre());
//            return "/faces/index.xhtml?faces-redirect=true";
            return "/dashboard.xhtml?faces-redirect=true";
        } catch (Exception e) {
            //JSFUtil.errorMessage(e, "doLogin()");
        }

        return "";
    }

    private void saveUserInSession(String username, int i) {
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="isValid">
    /**
     * verifica si es valido el usuario
     *
     * @return
     */
    private Boolean isUserValid() {
        Boolean isvalid = false;
        try {
            if (username.isEmpty() || username.equals("") || username == null) {
                JSFUtil.successMessage(rf.getAppMessage("warning.usernameisempty"));
                return false;
            }
            if (password.isEmpty() || password.equals("") || password == null) {
                JSFUtil.successMessage(rf.getAppMessage("warning.passwordisempty"));
                return false;
            }
            usuario.setUsername(username);
            Optional<Usuario> optional = Optional.ofNullable(usuarioRepository.find(usuario));

            if (!optional.isPresent()) {
                JSFUtil.addWarningMessage(rf.getAppMessage("login.usernamenotvalid"));
                return false;
            }else{
                Usuario u2 = optional.get();
//               usuario = optional.get();
                usuario = u2;
                if (!usuario.getPassword().equals(password)) {
                    JSFUtil.successMessage(rf.getAppMessage("login.passwordnotvalid"));
                    return false;
                }

               // if (!validadorRoles.validarRoles(usuario.getRol().getIdrol() )) {
               //     JSFUtil.successMessage(rf.getAppMessage("login.notienerolenelsistema") + " " + usuario.getRol().getIdrol());
               //     return false;
               //}

            }
            return true;
        } catch (Exception e) {
            JSFUtil.errorMessage("userValid() " + e.getLocalizedMessage());
        }
        return isvalid;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="sendToken()">
    public String sendToken() {
        try {

//            if(!myemail.equals("emailusuario")){
//                //no es el email del usuario
//            }
        } catch (Exception e) {
            JSFUtil.errorMessage("sendToken() " + e.getLocalizedMessage());
        }
        return "";
    }

    public String invalidateCurrentSession() {
        try {

        } catch (Exception e) {
            JSFUtil.successMessage("invalidateCurrentSession() " + e.getLocalizedMessage());
        }
        return "";
    }

    public String changePassword() {
        try {
            usuarioRepository.update(usuario);
            JSFUtil.successMessage(rf.getAppMessage("info.update"));
        } catch (Exception e) {
            JSFUtil.errorMessage(e.getLocalizedMessage());
        }
        return null;
    }
    // </editor-fold>
}