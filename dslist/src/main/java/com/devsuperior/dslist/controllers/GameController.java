package com.devsuperior.dslist.controllers;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.services.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = "/{id}")
    public GameDTO findById(@PathVariable Long id) {
        return gameService.findById(id);
    }

    @GetMapping
    public List<GameMinDTO> findAll() {
        return gameService.findAll();
    }

    @PostMapping
    public GameDTO add(@RequestBody Game body) {
        GameDTO gameDTO = new GameDTO(body);
        gameService.add(body);
        return gameDTO;
    }

    @GetMapping(value = "/search")
    public List<GameMinDTO> findGameByYear(@RequestParam (name = "game_year") Integer gameYear) {
        return gameService.findGameByYear(gameYear);
    }

}
