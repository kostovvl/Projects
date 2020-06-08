package springfundamentals.lab.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springfundamentals.lab.repository.OfferRepository;
import springfundamentals.lab.service.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper mapper;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper mapper) {
        this.offerRepository = offerRepository;
        this.mapper = mapper;
    }
}
