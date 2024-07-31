package org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.service.impl;


import org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.model.Player;
import org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.repository.IPlayerRepo;
import org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class PlayerService implements IPlayerService {
    @Autowired
    private IPlayerRepo playerRepo;
    private static final int MAX_REGISTERED_PLAYERS = 11;



    public String changePlayerStatus(Long id) {
        Player player = findById(id);

        if ("Dự bị".equals(player.getStatus())) {
            if (playerRepo.countByStatus("Đăng ký đá") >= MAX_REGISTERED_PLAYERS) {
                return "Số lượng cầu thủ đăng ký đá đã đủ 11 người.";
            } else {
                player.setStatus("Đăng ký đá");
            }
        } else if ("Đăng ký đá".equals(player.getStatus())) {
            player.setStatus("Dự bị");
        }

        playerRepo.save(player);
        return "Trạng thái cầu thủ đã được thay đổi.";
    }
    public Page<Player> getPlayer(int page, int size, String name, LocalDate dobMin, LocalDate dobMax) {
        if (size==0){
            size=5;
        }
        Pageable pageable = PageRequest.of(
                page, size, Sort.by("name").ascending().and(Sort.by("dob").descending())
        );
        return playerRepo.findAll(pageable,name, dobMin, dobMax );


    }

    @Override
    public Page<Player> findAll(int page, int size, String sort, String name, LocalDate dobMin, LocalDate dobMax) {
        return null;
    }

    @Override
    public Player findById(Long id) {
        return playerRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Player player) {
        playerRepo.save(player);
    }

    @Override
    public void remove(Long id) {
        playerRepo.deleteById(id);
    }

    @Override
    public Page<Player> findAllByNameContaining(String search, Pageable pageable) {
        return playerRepo.findAllByNameContaining(search, pageable);
    }

    @Override
    public Page<Player> findAllAndSort(String sort) {
        Sort sorted = Sort.by(sort.equals("increase") ? Sort.Direction.ASC : Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0,6,sorted);
        return playerRepo.findAll(pageable);
    }


}
