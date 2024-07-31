package org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.service.impl;

import org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.model.Club;
import org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.repository.IClubRepo;
import org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.service.IClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubService implements IClubService {
    @Autowired
    private IClubRepo clubRepo;
    @Override
    public Iterable<Club> findAll() {
        return clubRepo.findAll();
    }
}
