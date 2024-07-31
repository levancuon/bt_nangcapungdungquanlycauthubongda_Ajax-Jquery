package org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.repository;


import org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.model.Club;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IClubRepo extends PagingAndSortingRepository<Club,Long>, CrudRepository<Club,Long> {
}
