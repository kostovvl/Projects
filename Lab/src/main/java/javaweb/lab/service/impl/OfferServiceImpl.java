package javaweb.lab.service.impl;

import javaweb.lab.domain.dto.offerdto.OfferDto;
import javaweb.lab.domain.entity.Offer;
import javaweb.lab.exception.EntityDoesNotExistsException;
import javaweb.lab.exception.EntityExistsException;
import javaweb.lab.exception.EntityInvalidException;
import javaweb.lab.repository.OfferRepository;
import javaweb.lab.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper mapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper mapper) {
        this.offerRepository = offerRepository;
        this.mapper = mapper;
    }

    @Override
    public void createOffer(OfferDto offerDto) throws EntityExistsException {

        if (this.offerRepository.findByHeading(offerDto.getHeading()) != null) {
            throw new EntityExistsException();
        }

        Offer newOffer = this.mapper.map(offerDto, Offer.class);
        this.offerRepository.saveAndFlush(newOffer);
    }

    @Override
    public void deleteOffer(OfferDto offerDto) throws EntityDoesNotExistsException {

        if (this.offerRepository.findByHeading(offerDto.getHeading()) == null) {
            throw new EntityDoesNotExistsException();
        }

        Offer forDeletion = this.offerRepository.findByHeading(offerDto.getHeading());
        this.offerRepository.deleteById(forDeletion.getId());

    }

    @Override
    public void updateOffer(OfferDto offerDto) throws EntityInvalidException {

        if (this.offerRepository.findByHeading(offerDto.getHeading()) == null) {
            throw new EntityInvalidException();
        }

        Offer forUpdate = this.offerRepository.findByHeading(offerDto.getHeading());
        forUpdate.setModified(new Date());
        this.offerRepository.saveAndFlush(forUpdate);

    }

    @Override
    public long count() {
        return this.offerRepository.count();
    }
}
