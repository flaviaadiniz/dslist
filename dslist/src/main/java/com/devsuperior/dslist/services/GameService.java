package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        try {
            Game result = gameRepository.findById(id).get();
            return new GameDTO(result);
        } catch (NoSuchElementException elementException) {
            System.out.println("There's no game with the ID provided.");
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> result = gameRepository.findAll();
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByGameList(Long listId) {
        List<GameMinProjection> games = gameRepository.searchByList(listId);
        return games.stream().map(x -> new GameMinDTO(x)).toList();
    }

    public Game add(Game body) {
        return gameRepository.save(body);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findGameByYear(Integer gameYear) {
        List<Game> result = gameRepository.findGameByYear(gameYear);
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }
}
