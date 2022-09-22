package com.projetoapi.webclientrickandmortyapi.controller;

import com.projetoapi.webclientrickandmortyapi.client.RickAndMortyClient;
import com.projetoapi.webclientrickandmortyapi.response.CharacterResponse;
import com.projetoapi.webclientrickandmortyapi.response.EpisodeResponse;
import com.projetoapi.webclientrickandmortyapi.response.ListOfEpisodeResponse;
import com.projetoapi.webclientrickandmortyapi.response.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping ("/webclient")
public class RickAndMortyController {

    RickAndMortyClient rickAndMortyClient;

    @GetMapping("/character/{id}")
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id) {
        return rickAndMortyClient.findCharacterById(id);
    }

    @GetMapping("/location/{id}")
    public Mono<LocationResponse> getLocationById(@PathVariable String id) {
        return rickAndMortyClient.findAnLocationById(id);
    }

    @GetMapping("/episode/{id}")
    public Mono<EpisodeResponse> getEpisodeById(@PathVariable String id) {
        return rickAndMortyClient.findAnEpisodeById(id);
    }

    @GetMapping("/episodes")
    public Flux<ListOfEpisodeResponse> getAllEpisodes() {
        return rickAndMortyClient.getAllEpisodes();
    }
}
