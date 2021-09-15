package com.berroteran.academia.services;

import com.berroteran.academia.avbravoutils.JsfUtil;
import com.berroteran.academia.ejbspard.repository.BodegaRepository;
import com.berroteran.academia.entity.Bodega;
import org.bson.Document;

import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @authoravbravo
 */
@Stateless
public class BodegaServices {

    @Inject
    BodegaRepository bodegaRepository;

    public List<Bodega> complete(String query) {
        List<Bodega> suggestions = new ArrayList<>();
        try {
            query = query.trim();
            if (query.length() < 1) {
                return suggestions;
            }
            String field = (String) UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("field");
            suggestions=  bodegaRepository.findRegex(field,query,true,new Document(field,1));

        } catch (Exception e) {
            JsfUtil.errorMessage("complete() " + e.getLocalizedMessage());
        }
        return suggestions;
    }

}