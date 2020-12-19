package io.github.jr.developers.txmanager.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

//    @Transactional
    public Club create(Club club) {
        return clubRepository.save(club);
    }
}
