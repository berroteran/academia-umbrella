package com.berroteran.academia.ui.converter;


import com.berroteran.academia.entity.Bodega;
import com.berroteran.academia.repository.BodegaRepository;
import com.berroteran.academia.util.JSFUtil;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
@Named
@RequestScoped
public class BodegaConverter implements Converter {

    @Inject
    BodegaRepository bodegaRepository;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Bodega bodega = new Bodega();
        try{
            if(!s.equals("null")){
                Bodega b = new Bodega();
                b.setIdbodega( Integer.valueOf(s));
                Optional<Bodega> optional = Optional.ofNullable(bodegaRepository.find(b));
                if (optional.isPresent()) {
                    bodega= optional.get();
                }
            }
        } catch (Exception e) {
            JSFUtil.errorMessage("getAsObject()" + e.getLocalizedMessage());
        }
        return bodega;
    }


    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String r = "";
        try {
            if (o instanceof Bodega) {
                Bodega bodega = (Bodega) o;
                r = String.valueOf(bodega.getIdbodega());
            }else if (o instanceof String) {
                r = (String) o;
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage("getAsString()" + e.getLocalizedMessage());
        }
        return r;
    }

}