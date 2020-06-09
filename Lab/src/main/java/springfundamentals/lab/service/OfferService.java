package springfundamentals.lab.service;

import springfundamentals.lab.domain.dto.OfferGetDto;
import springfundamentals.lab.domain.dto.OfferSetDto;

import java.util.List;

public interface OfferService {

    void addNewOffer(OfferSetDto offerSetDto);
    List<OfferGetDto> getAllOffers();
    OfferGetDto findById(long id);

}
