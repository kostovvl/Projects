package softuni.workshop.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.workshop.utils.ValidatorUtil;
import softuni.workshop.utils.impl.ValidatorUtilImpl;

@Configuration
public class AppBeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    public ValidatorUtil validatorUtil () {
        return new ValidatorUtilImpl();
    }

}
