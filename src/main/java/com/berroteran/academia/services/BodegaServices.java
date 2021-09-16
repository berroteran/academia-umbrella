package com.berroteran.academia.services;

import com.berroteran.academia.repository.BodegaRepository;
import jakarta.ejb.Stateless;

import javax.inject.Inject;

/**
 *
 * @authoravbravo
 */
@Stateless
public class BodegaServices {

    @Inject
    BodegaRepository bodegaRepository;

}