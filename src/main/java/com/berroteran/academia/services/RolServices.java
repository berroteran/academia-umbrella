package com.berroteran.academia.services;

import com.berroteran.academia.entity.Rol;
import com.berroteran.academia.repository.RolRepository;
import jakarta.ejb.Stateless;

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
public class RolServices {

    @Inject
    RolRepository rolRepository;


}