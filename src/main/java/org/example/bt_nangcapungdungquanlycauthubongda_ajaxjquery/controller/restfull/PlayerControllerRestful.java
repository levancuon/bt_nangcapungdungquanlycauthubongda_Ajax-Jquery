package org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.controller.restfull;

import org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.model.Player;
import org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/player")
@CrossOrigin("*")
public class PlayerControllerRestful {
    @Autowired
    private IPlayerService playerService;

    @GetMapping("/list")
    public ResponseEntity<?> players(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate dobMin,
            @RequestParam(required = false) LocalDate dobMax
    ) {
        Page<Player> players = playerService.getPlayer(page, size, name, dobMin, dobMax);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        playerService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findById(@PathVariable("id") Long id) {
        Player player = playerService.findById(id);
        if (player != null) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody Player player, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        playerService.save(player);
        return new ResponseEntity<>(player,HttpStatus.OK);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @Validated @RequestBody Player player, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Player player1 = playerService.findById(id);
        if (player1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        player1.setId(player.getId());
        player1.setName(player.getName());
        player1.setExperience(player.getExperience());
        player1.setDob(player.getDob());
        player1.setClub(player.getClub());
        player1.setPosition(player.getPosition());
        playerService.save(player1);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
