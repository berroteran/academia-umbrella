package com.berroteran.academia.controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// <editor-fold defaultstate="collapsed" desc="imports">

import com.berroteran.academia.entity.Rol;
import com.berroteran.academia.entity.Usuario;
import com.berroteran.academia.roles.ValidadorRoles;
import com.berroteran.academia.util.JSFUtil;
import com.berroteran.academia.util.ResourcesFiles;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
public class LoginController  implements Serializable, SecurityInterface  {
    // <editor-fold defaultstate="collapsed" desc="fields">
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    //Acceso
    @Inject
    AccessInfoServices accessInfoServices;
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
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="getter/setter">
    public String getMyemail() {
        return myemail;
    }
    public void setMyemail(String myemail) {
        this.myemail = myemail;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    public Boolean getTokenwassend() {
        return tokenwassend;
    }
    public void setTokenwassend(Boolean tokenwassend) {
        this.tokenwassend = tokenwassend;
    }
    public String getMytoken() {
        return mytoken;
    }
    public void setMytoken(String mytoken) {
        this.mytoken = mytoken;
    }
    public String getUsernameSelected() {
        return usernameSelected;
    }
    public void setUsernameSelected(String usernameSelected) {
        this.usernameSelected = usernameSelected;
    }
    public Boolean getUserwasLoged() {
        return userwasLoged;
    }
    public void setUserwasLoged(Boolean userwasLoged) {
        this.userwasLoged = userwasLoged;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="init">
    @PostConstruct
    public void init() {
        loggedIn = false;
        recoverSession = false;
        userwasLoged = false;
        tokenwassend = false;
    }
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="destroy">
    @PreDestroy
    public void destroy() {
    }
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="constructor">
    public LoginController() {
    }
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="irLogin">
    public String irLogin() {
//        return "/faces/login";
        return "/login";
    }
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="doLogin">
    public String doLogin() {
        try {

            tokenwassend = false;
            userwasLoged = false;
            loggedIn = true;
            usuario= new Usuario();
            if (username == null || password == null) {
                JsfUtil.warningMessage(rf.getAppMessage("login.usernamenotvalid"));
                return null;
            }
            usernameRecover = usernameRecoveryOfSession();
            recoverSession = !usernameRecover.equals("");
            if (recoverSession) {
                invalidateCurrentSession();
                RequestContext.getCurrentInstance().execute("PF('sessionDialog').show();");
                return "";
            }
            if (recoverSession && usernameRecover.equals(username)) {
            } else {
                if (isUserLogged(username)) {
                    userwasLoged = true;
                    JsfUtil.warningMessage(rf.getAppMessage("login.alreadylogged"));
                    return "";
                }

            }
            if (!isUserValid()) {
                accessInfoRepository.save(accessInfoServices.generateAccessInfo(username, "login",rf.getAppMessage("login.usernameorpasswordnotvalid")));
                JsfUtil.warningMessage(rf.getAppMessage("login.usernameorpasswordnotvalid"));
                return " ";

            }
            saveUserInSession(username, 2100);
            accessInfoRepository.save(accessInfoServices.generateAccessInfo(username, "login",rf.getAppMessage("login.welcome") ));
            loggedIn = true;
            foto = "img/me.jpg";
            JsfUtil.successMessage(rf.getAppMessage("login.welcome") + " " + usuario.getNombre());
//            return "/faces/index.xhtml?faces-redirect=true";
            return "/dashboard.xhtml?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.errorMessage(e, "doLogin()");
        }
        return "";
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
            Optional<Usuario> optional = usuarioRepository.findById(usuario);
            if (!optional.isPresent()) {
                JSFUtil.warningMessage(rf.getAppMessage("login.usernamenotvalid"));
                return false;
            }else{
                Usuario u2 = optional.get();
//               usuario = optional.get();
                usuario = u2;
                if (!usuario.getPassword().equals(password)) {
                    JSFUtil.successMessage(rf.getAppMessage("login.passwordnotvalid"));
                    return false;
                }
                if (!validadorRoles.validarRoles(usuario.getRol().getIdrol() )) {
                    JSFUtil.successMessage(rf.getAppMessage("login.notienerolenelsistema") + " " + usuario.getRol().getIdrol());
                    return false;
                }
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
            ManagerEmail managerEmail = new ManagerEmail();
            String token = tokenOfUsername(username);
            if (!token.equals("")) {

                String texto = rf.getAppMessage("token.forinitsession")+  " " + token +  rf.getAppMessage("token.forinvalidate ");
                if (managerEmail.send(myemail, rf.getAppMessage("token.tokenofsecurity"), texto, "adminemail@gmail.com", "adminpasswordemail"))
                {
                    JsfUtil.successMessage(rf.getAppMessage("token.wassendtoemail"));
                    tokenwassend = true;
                } else {
                    JsfUtil.warningMessage(rf.getAppMessage("token.errortosendemail"));
                }
            } else {
                JsfUtil.warningMessage(rf.getAppMessage("token.asiganedtouser"));
            }

        } catch (Exception e) {
            JsfUtil.errorMessage("sendToken() " + e.getLocalizedMessage());
        }
        return "";
    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="destroyByUser()">
    public String destroyByUser() {
        try {
            if (isUserValid()) {
                userwasLoged = !destroyByUsername(username);
                if (!userwasLoged) {
                    JsfUtil.successMessage(rf.getAppMessage("session.destroyedloginagain"));
                } else {
                    JsfUtil.successMessage(rf.getAppMessage("session.notdestroyed"));
                }
            } else {
                JsfUtil.warningMessage(rf.getAppMessage("warning.usernotvalid"));
            }
        } catch (Exception e) {
            JsfUtil.errorMessage("destroyByUser() " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="destroyWithToken()">

    public String destroyByToken() {
        try {
            if (isUserValid()) {
                userwasLoged = !destroyByToken(username, mytoken);

            } else {
                JsfUtil.warningMessage("Los datos del usuario no son validos");
            }
        } catch (Exception e) {
            JsfUtil.warningMessage(rf.getAppMessage("warning.usernotvalid"));
        }
        return "";
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="invalidateCurrentSession">

    public String invalidateCurrentSession() {
        try {
            if (invalidateMySession()) {
                JsfUtil.successMessage(rf.getAppMessage("sesion.invalidate"));
            } else {
                JsfUtil.warningMessage(rf.getAppMessage("sesion.errortoinvalidate"));
            }

        } catch (Exception e) {
            JsfUtil.successMessage("invalidateCurrentSession() " + e.getLocalizedMessage());
        }
        return "";
    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="doLogout">
    public String doLogout() {
        return logout("/spardjsd/faces/login.xhtml?faces-redirect=true");
    }
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="changePassword">
    public String changePassword() {
        try {
            usuarioRepository.update(usuario);
            JsfUtil.successMessage(rf.getAppMessage("info.update"));
        } catch (Exception e) {
            JsfUtil.errorMessage(e.getLocalizedMessage());
        }
        return null;
    }
    // </editor-fold>



}