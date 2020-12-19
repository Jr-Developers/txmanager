package io.github.jr.developers.txmanager;

import io.github.jr.developers.txmanager.club.Club;
import io.github.jr.developers.txmanager.club.ClubService;
import io.github.jr.developers.txmanager.user.User;
import io.github.jr.developers.txmanager.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@SpringBootApplication
@RestController
public class TxmanagerApplication {

    @Autowired
    private ClubService clubService;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(TxmanagerApplication.class, args);
    }

    @GetMapping("/commit")
    @Transactional
    public void commit() throws InterruptedException {
        Club club = new Club();
        club.setId("Commit");
        User user = new User();
        user.setId("Commit");

        clubService.create(club);
        Thread.sleep(3000);
        userService.create(user);
        Thread.sleep(3000);

    }

    @GetMapping("/rollback")
    @Transactional
    public void rollback() throws InterruptedException {
        Club club = new Club();
        club.setId("Rollback");
        User user = new User();
        user.setId("Rollback");

        clubService.create(club);
        Thread.sleep(3000);
        userService.create(user);
        Thread.sleep(3000);
        throw new RuntimeException("강제 Exception 발생");
    }
}
