package com.projetoapi.webclientrickandmortyapi.client;

import com.projetoapi.webclientrickandmortyapi.response.CharacterResponse;
import com.projetoapi.webclientrickandmortyapi.response.EpisodeResponse;
import com.projetoapi.webclientrickandmortyapi.response.ListOfEpisodeResponse;
import com.projetoapi.webclientrickandmortyapi.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j //Permite a criacao de programa, frameworks e bibliotecas independente da implementacao concreta de log a ser utilizada
public class RickAndMortyClient {

    private final WebClient webClient;

    public RickAndMortyClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    //Mono trata de um dado só
    public Mono<CharacterResponse> findCharacterById(String id){
        log.info("Buscando o personagem com o id [{}]", id);
        return webClient
                .get()
                .uri("/character/"+ id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToMono(CharacterResponse.class);
    }

    public Mono<LocationResponse> findAnLocationById(String id){
        log.info("Buscando a localização com o id [{}]", id);
        return webClient
                .get()
                .uri("/location/"+ id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToMono(LocationResponse.class);
    }

    public Mono<EpisodeResponse> findAnEpisodeById(String id){
        log.info("Buscando o episodio com o id [{}]", id);
        return webClient
                .get()
                .uri("/episode/"+ id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToMono(EpisodeResponse.class);
    }

    //O Flux retorna varios dados da API
    public Flux<ListOfEpisodeResponse> getAllEpisodes(){
        log.info("Listando todos os episodios");
        return webClient
                .get()
                .uri("/episode/")
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToFlux(ListOfEpisodeResponse.class);
    }


}
