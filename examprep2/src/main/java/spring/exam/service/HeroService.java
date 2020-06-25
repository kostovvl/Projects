package spring.exam.service;

import spring.exam.domain.dto.HeroDto;
import spring.exam.domain.view.HeroView;

import java.util.List;

public interface HeroService {

    void createHero(HeroDto heroDto);
    List<HeroView> getAllHeroes();
    HeroView findById(Long id);
    void deleteById(Long id);

}
