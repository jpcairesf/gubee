package br.com.gubee.interview.core.features.hero.validator;

import br.com.gubee.interview.core.features.common.exception.BusinessException;
import br.com.gubee.interview.core.features.hero.repository.HeroRepository;
import br.com.gubee.interview.model.HeroInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class HeroValidator {

    private HeroRepository repository;

    public void validateName(HeroInput input) {
        if(repository.existsByName(input.getName()))
            throw new BusinessException(format("Já existe um Hero com o nome \"%s\".", input.getName()));
    }

}
