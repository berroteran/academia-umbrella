package com.berroteran.academia.controller;


import com.berroteran.academia.entity.Bodega;
import com.berroteran.academia.model.BodegaDataModel;
import com.berroteran.academia.repository.BodegaRepository;
import com.berroteran.academia.services.BodegaServices;
import com.berroteran.academia.services.UserInfoServices;
import com.berroteran.academia.util.JSFUtil;
import com.berroteran.academia.util.ResourcesFiles;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
// </editor-fold>

/**
 *
 * @authoravbravo
 */
@Named
@ViewScoped
public class BodegaController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean writable = false;

    Integer page = 1;
    Integer rowPage = 25;
    List<Integer> pages = new ArrayList<>();

    //Entity
    Bodega bodega;
    Bodega bodegaSelected;

    //List
    List<Bodega> bodegaList = new ArrayList<>();
    List<Bodega> bodegaFiltered = new ArrayList<>();
    //DataModel
    private BodegaDataModel bodegaDataModel;

    //Repository
    @Inject
    BodegaRepository bodegaRepository;

    //Services
    @Inject
    UserInfoServices userInfoServices;

    @Inject
    BodegaServices bodegaServices;

    @Inject
    ResourcesFiles rf;

    @Inject
    LoginController loginController;

    public BodegaController() {
    }

    @PostConstruct
    public void init() {
        try {

            writable = false;

            bodegaList = new ArrayList<>();
            bodegaFiltered = new ArrayList<>();
            bodega = new Bodega();
            bodegaDataModel = new BodegaDataModel(bodegaList);
            String idbodega = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idbodega");


        } catch (Exception e) {
            JSFUtil.errorMessage("init() " + e.getLocalizedMessage());
        }
    }


}