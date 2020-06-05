package javaweb.lab.service;

import javaweb.lab.domain.dto.offerdto.OfferDto;
import javaweb.lab.exception.EntityDoesNotExistsException;
import javaweb.lab.exception.EntityExistsException;
import javaweb.lab.exception.EntityInvalidException;

public interface OfferService {

    void createOffer(OfferDto offerDto) throws EntityExistsException;
    void deleteOffer(OfferDto offerDto) throws EntityDoesNotExistsException;
    void updateOffer(OfferDto offerDto) throws EntityInvalidException;
    long count();

}
