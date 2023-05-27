package br.com.gubee.interview.core.features.hero.controller;

import br.com.gubee.interview.core.features.hero.service.HeroService;
import br.com.gubee.interview.core.features.hero.model.HeroComparisonOutput;
import br.com.gubee.interview.model.HeroInput;
import br.com.gubee.interview.model.Hero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/hero")
@RequiredArgsConstructor
public class HeroController {

    private final HeroService service;

    @PostMapping("/create")
    public ResponseEntity<Hero> create(@RequestBody @Valid HeroInput input, UriComponentsBuilder uriBuilder) {
        Hero output = service.create(input);
        URI uri = uriBuilder.path("/api/hero/{id}").buildAndExpand(output.getId()).toUri();
        return created(uri).body(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> findById(@PathVariable UUID id) {
        return status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Hero>> findByFilter(@RequestParam(value = "name", required = false) String name) {
        return status(HttpStatus.OK).body(service.findByFilter(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hero> update(@PathVariable UUID id, @RequestBody @Valid HeroInput input) {
        return status(HttpStatus.OK).body(service.update(id, input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ok().build();
    }

    @GetMapping("/compare")
    public ResponseEntity<HeroComparisonOutput> compare(
            @RequestParam(value = "first") UUID first,
            @RequestParam(value = "second") UUID second) {
        return status(HttpStatus.OK).body(service.getComparisonById(first, second));
    }

}
