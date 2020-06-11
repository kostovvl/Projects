package springfundamentals.lab2.service;

import springfundamentals.lab2.domain.dto.OfferGetDto;
import springfundamentals.lab2.domain.dto.OfferSetDto;

import java.util.List;

public interface OfferService {

    void addNewOffer(OfferSetDto offerSetDto);
    List<OfferGetDto> getAllOffers();
    OfferGetDto findById(long id);

}
