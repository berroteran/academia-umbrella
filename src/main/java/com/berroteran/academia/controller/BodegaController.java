package com.berroteran.academia.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// <editor-fold defaultstate="collapsed" desc="imports">

import com.berroteran.academia.avbravoutils.JsfUtil;
import com.berroteran.academia.avbravoutils.printer.Printer;
import com.berroteran.academia.ejbjmoordb.services.RevisionHistoryServices;
import com.berroteran.academia.ejbjmoordb.services.UserInfoServices;
import com.berroteran.academia.entity.Bodega;
import com.berroteran.academia.model.BodegaDataModel;
import com.berroteran.academia.repository.BodegaRepository;
import com.berroteran.academia.repository.RevisionHistoryRepository;
import com.berroteran.academia.services.BodegaServices;
import com.berroteran.academia.spard.util.ResourcesFiles;
import org.bson.Document;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
// </editor-fold>

/**
 *
 * @authoravbravo
 */
@Named
@ViewScoped
public class BodegaController implements Serializable {
// <editor-fold defaultstate="collapsed" desc="fields">

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
    @Inject
    RevisionHistoryRepository revisionHistoryRepository;

    //Services
    @Inject
    RevisionHistoryServices revisionHistoryServices;
    @Inject
    UserInfoServices userInfoServices;
    @Inject
    BodegaServices bodegaServices;
    @Inject
    ResourcesFiles rf;
    @Inject
    Printer printer;
    @Inject
    LoginController loginController;

    //List of Relations
    //Repository of Relations
    // </editor-fold>

// <editor-fold defaultstate="collapsed" desc="getter/setter">
    public List<Integer> getPages() {

        return bodegaRepository.listOfPage(rowPage);
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRowPage() {
        return rowPage;
    }

    public void setRowPage(Integer rowPage) {
        this.rowPage = rowPage;
    }

    public BodegaServices getBodegaServices() {
        return bodegaServices;
    }

    public void setBodegaServices(BodegaServices bodegaServices) {
        this.bodegaServices = bodegaServices;
    }

    public List<Bodega> getBodegaList() {
        return bodegaList;
    }

    public void setBodegaList(List<Bodega> bodegaList) {
        this.bodegaList = bodegaList;
    }

    public List<Bodega> getBodegaFiltered() {
        return bodegaFiltered;
    }

    public void setBodegaFiltered(List<Bodega> bodegaFiltered) {
        this.bodegaFiltered = bodegaFiltered;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public Bodega getBodegaSelected() {
        return bodegaSelected;
    }

    public void setBodegaSelected(Bodega bodegaSelected) {
        this.bodegaSelected = bodegaSelected;
    }

    public BodegaDataModel getBodegaDataModel() {
        return bodegaDataModel;
    }

    public void setBodegaDataModel(BodegaDataModel bodegaDataModel) {
        this.bodegaDataModel = bodegaDataModel;
    }

    public Boolean getWritable() {
        return writable;
    }

    public void setWritable(Boolean writable) {
        this.writable = writable;
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="constructor">
    public BodegaController() {
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="preRenderView()">
    @Override
    public String preRenderView(String action) {
        //acciones al llamar el formulario despues del init
        return "";
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="init">

    @PostConstruct
    public void init() {
        try {

            writable = false;

            bodegaList = new ArrayList<>();
            bodegaFiltered = new ArrayList<>();
            bodega = new Bodega();
            bodegaDataModel = new BodegaDataModel(bodegaList);
            String idbodega = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idbodega");

            if (idbodega != null) {
                Optional<Bodega> optional = bodegaRepository.find("idbodega", idbodega);
                if (optional.isPresent()) {
                    bodega = optional.get();
                    bodegaSelected = bodega;
                    writable = true;
                    RequestContext.getCurrentInstance().update(":form:content");
                }
            } else {

                if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pagebodega") != null) {
                    String temp = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pagebodega").toString();
                    if (temp != null) {
                        page = Integer.parseInt(temp);
                        Integer c = bodegaRepository.sizeOfPage(rowPage);
                        page = page > c ? c : page;

                    }

                }
                move();

            }

        } catch (Exception e) {
            JsfUtil.errorMessage("init() " + e.getLocalizedMessage());
        }
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="reset">

    @Override
    public void reset() {

        RequestContext.getCurrentInstance().reset(":form:content");
        prepare("new");
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="prepare(String action, Object... item)">
    @Override
    public String prepare(String action, Object... item) {
        String url = "";
        try {
            switch (action) {
                case "new":
                    bodega = new Bodega();
                    bodegaSelected = new Bodega();

                    writable = false;
                    break;

                case "edit":
                    if (item.length != 0) {
                        bodegaSelected = (Bodega) item[0];
                        bodega = bodegaSelected;
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pagebodega", page.toString());
                    }

                    url = "/pages/bodega/view.xhtml";
                    break;
                case "golist":
                    url = "/pages/bodega/list.xhtml";
                    break;

                case "gonew":
                    url = "/pages/bodega/new.xhtml";
                    break;
            }

        } catch (Exception e) {
            JsfUtil.errorMessage("prepare() " + e.getLocalizedMessage());
        }

        return url;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="showAll">
    @Override
    public String showAll() {
        try {
            bodegaList = new ArrayList<>();
            bodegaFiltered = new ArrayList<>();
            bodegaList = bodegaRepository.findAll();
            bodegaFiltered = bodegaList;
            bodegaDataModel = new BodegaDataModel(bodegaList);

        } catch (Exception e) {
            JsfUtil.errorMessage("showAll()" + e.getLocalizedMessage());
        }
        return "";
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="isNew">

    @Override
    public String isNew() {
        try {
            writable = true;
            Optional<Bodega> optional = bodegaRepository.findById(bodega);
            if (optional.isPresent()) {
                writable = false;

                JsfUtil.warningMessage(rf.getAppMessage("warning.idexist"));
                return "";
            } else {
                String id = bodega.getIdbodega();
                bodega = new Bodega();
                bodega.setIdbodega(id);
                bodegaSelected = new Bodega();
            }

        } catch (Exception e) {
            JsfUtil.errorMessage("isNew()" + e.getLocalizedMessage());
        }
        return "";
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="save">

    @Override
    public String save() {
        try {
            Optional<Bodega> optional = bodegaRepository.findById(bodega);
            if (optional.isPresent()) {
                JsfUtil.warningDialog(rf.getAppMessage("info.message"), rf.getAppMessage("warning.idexist"));
                return null;
            }

            //Lo datos del usuario
            bodega.setUserInfo(userInfoServices.generateListUserinfo(loginController.getUsername(), "create"));
            if (bodegaRepository.save(bodega)) {
                JsfUtil.successMessage(rf.getAppMessage("info.save"));
                reset();
            } else {
                JsfUtil.successMessage("save() " + bodegaRepository.getException().toString());
            }

        } catch (Exception e) {
            JsfUtil.errorMessage("save()" + e.getLocalizedMessage());
        }
        return "";
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="edit">

    @Override
    public String edit() {
        try {

            bodega.getUserInfo().add(userInfoServices.generateUserinfo(loginController.getUsername(), "update"));

            //guarda el contenido anterior
            revisionHistoryRepository.save(revisionHistoryServices.getRevisionHistory(bodegaSelected.getIdbodega(), loginController.getUsername(),
                    "beforeupdate", "bodega", bodegaRepository.toDocument(bodegaSelected).toString()));

            //guarda el contenido actualizado
            revisionHistoryRepository.save(revisionHistoryServices.getRevisionHistory(bodega.getIdbodega(), loginController.getUsername(),
                    "update", "bodega", bodegaRepository.toDocument(bodega).toString()));

            bodegaRepository.update(bodega);
            JsfUtil.successMessage(rf.getAppMessage("info.update"));
        } catch (Exception e) {
            JsfUtil.errorMessage("edit()" + e.getLocalizedMessage());
        }
        return "";
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="delete(Object item, Boolean donotleave, Boolean reset, Boolean removeFromList)">

    @Override
    public String delete(Object item, Boolean deleteonviewpage) {
        String path = "";
        try {
            bodega = (Bodega) item;

            bodegaSelected = bodega;
            if (bodegaRepository.delete("idbodega", bodega.getIdbodega())) {
                revisionHistoryRepository.save(revisionHistoryServices.getRevisionHistory(bodega.getIdbodega(), loginController.getUsername(), "delete", "bodega", bodegaRepository.toDocument(bodega).toString()));
                JsfUtil.successMessage(rf.getAppMessage("info.delete"));

                if (!deleteonviewpage) {
                    bodegaList.remove(bodega);
                    bodegaFiltered = bodegaList;
                    bodegaDataModel = new BodegaDataModel(bodegaList);

                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pagebodega", page.toString());

                } else {
                    bodega = new Bodega();
                    bodegaSelected = new Bodega();
                    writable = false;

                }

            }

        } catch (Exception e) {
            JsfUtil.errorMessage("delete() " + e.getLocalizedMessage());
        }
        path = deleteonviewpage ? "/pages/bodega/list.xhtml" : "";
        return path;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="deleteAll">
    @Override
    public String deleteAll() {
        if (bodegaRepository.deleteAll() != 0) {
            JsfUtil.successMessage(rf.getAppMessage("info.delete"));
        }
        return "";
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="print">

    @Override
    public String print() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pagebodega", page.toString());
            List<Bodega> list = new ArrayList<>();
            list.add(bodega);
            String ruta = "/resources/reportes/bodega/details.jasper";
            HashMap parameters = new HashMap();
            // parameters.put("P_parametro", "valor");
            printer.imprimir(list, ruta, parameters);
        } catch (Exception ex) {
            JsfUtil.errorMessage("imprimir() " + ex.getLocalizedMessage());
        }
        return null;
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="printAll">

    @Override
    public String printAll() {
        try {
            List<Bodega> list = new ArrayList<>();
            list = bodegaRepository.findAll(new Document("idbodega", 1));

            String ruta = "/resources/reportes/bodega/all.jasper";
            HashMap parameters = new HashMap();
            // parameters.put("P_parametro", "valor");
            printer.imprimir(list, ruta, parameters);
        } catch (Exception ex) {
            JsfUtil.errorMessage("imprimir() " + ex.getLocalizedMessage());
        }
        return null;
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="handleSelect">

    public void handleSelect(SelectEvent event) {
        try {
            bodegaList.removeAll(bodegaList);
            bodegaList.add(bodegaSelected);
            bodegaFiltered = bodegaList;
            bodegaDataModel = new BodegaDataModel(bodegaList);
        } catch (Exception ex) {
            JsfUtil.errorMessage("handleSelect() " + ex.getLocalizedMessage());
        }
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="last">
    @Override
    public String last() {
        try {
            page = bodegaRepository.sizeOfPage(rowPage);
            move();
        } catch (Exception e) {
            JsfUtil.errorMessage("last() " + e.getLocalizedMessage());
        }
        return "";
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="first">

    @Override
    public String first() {
        try {
            page = 1;
            move();
        } catch (Exception e) {
            JsfUtil.errorMessage("first() " + e.getLocalizedMessage());
        }
        return "";
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="next">

    @Override
    public String next() {
        try {
            if (page < (bodegaRepository.sizeOfPage(rowPage))) {
                page++;
            }
            move();
        } catch (Exception e) {
            JsfUtil.errorMessage("next() " + e.getLocalizedMessage());
        }
        return "";
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="back">

    @Override
    public String back() {
        try {
            if (page > 1) {
                page--;
            }
            move();
        } catch (Exception e) {
            JsfUtil.errorMessage("back() " + e.getLocalizedMessage());
        }
        return "";
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="skip(Integer page)">

    @Override
    public String skip(Integer page) {
        try {
            this.page = page;
            move();
        } catch (Exception e) {
            JsfUtil.errorMessage("skip() " + e.getLocalizedMessage());
        }
        return "";
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="move">

    @Override
    public void move() {
        try {

            bodegaList = bodegaRepository.findPagination(page, rowPage);

            bodegaFiltered = bodegaList;

            bodegaDataModel = new BodegaDataModel(bodegaList);

        } catch (Exception e) {
            JsfUtil.errorMessage("move() " + e.getLocalizedMessage());
        }
    }// </editor-fold>

}