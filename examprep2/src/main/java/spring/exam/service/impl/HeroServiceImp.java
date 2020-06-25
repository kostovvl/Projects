package spring.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spring.exam.domain.dto.HeroDto;
import spring.exam.domain.entity.Hero;
import spring.exam.domain.view.HeroView;
import spring.exam.repository.HeroRepository;
import spring.exam.service.HeroService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeroServiceImp implements HeroService {

    private final HeroRepository heroRepository;
    private final ModelMapper mapper;

    public HeroServiceImp(HeroRepository heroRepository, ModelMapper mapper) {
        this.heroRepository = heroRepository;
        this.mapper = mapper;
    }

    @Override
    public void createHero(HeroDto heroDto) {

        this.heroRepository.saveAndFlush(this.mapper.map(heroDto, Hero.class));

    }

    @Override
    public List<HeroView> getAllHeroes() {

        return this.heroRepository.findAll().stream()
                .map(h -> {
                    HeroView heroView = this.mapper.map(h, HeroView.class);
                    heroView.setImage(String.format("/img/%s.jpg", h.getHeroClass().name()));
                    return heroView;
                }).collect(Collectors.toList());
    }

    @Override
    public HeroView findById(Long id) {

        Hero hero = this.heroRepository.findById(id).orElse(null);
        HeroView heroView = this.mapper.map(hero, HeroView.class);
        heroView.setImage(String.format("/img/%s.jpg", hero.getHeroClass().name()));

        return heroView;
    }

    @Override
    public void deleteById(Long id) {
        this.heroRepository.deleteById(id);
    }
}
