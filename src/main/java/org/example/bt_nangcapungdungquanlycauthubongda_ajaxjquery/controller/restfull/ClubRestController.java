package org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.controller.restfull;

import org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.model.Club;
import org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.service.IClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/club")
public class ClubRestController {
    @Autowired
    private IClubService clubService;

    @GetMapping("/clubs")
    public ResponseEntity<?> getClubs() {
        Iterable<Club> clubs = clubService.findAll();
        return new ResponseEntity<>(clubs, HttpStatus.OK);
    }
}
