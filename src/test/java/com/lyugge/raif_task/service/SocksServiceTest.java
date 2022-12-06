package com.lyugge.raif_task.service;

import com.lyugge.raif_task.domain.Socks;
import com.lyugge.raif_task.enums.Operations;
import com.lyugge.raif_task.exceptions.NullResultException;
import com.lyugge.raif_task.repos.SocksRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SocksServiceTest {

    @Autowired
    private SocksService socksService;

    @MockBean
    private SocksRepository socksRepository;


    @Test
    void getByColorAndCottonPart() {
        Socks socks = new Socks();
        socks.setQuantity(3L);
        socks.setColor("black");
        socks.setCottonPart(78L);
        socksService.addSocks(socks);
        Socks socksFromBD = socksService.getByColorAndCottonPart(socks.getColor(),
                Operations.equals, 78L);
        Assertions.assertEquals(socks.getColor(), socksFromBD.getColor());
        Assertions.assertEquals(socks.getCottonPart(), socksFromBD.getCottonPart());
        Assertions.assertEquals(socks.getQuantity(), socksFromBD.getQuantity());
        Assertions.assertThrows(NullResultException.class, () -> {socksService.getByColorAndCottonPart(socks.getColor(),
                Operations.moreThan, 90L);});
        Assertions.assertThrows(NullResultException.class, () -> {socksService.getByColorAndCottonPart(socks.getColor(),
                Operations.equals, 90L);});

        socksFromBD = socksService.getByColorAndCottonPart(socks.getColor(),
                Operations.lessThan, 100L);
        Assertions.assertEquals(socks.getColor(), socksFromBD.getColor());
        Assertions.assertEquals(socks.getCottonPart(), socksFromBD.getCottonPart());
        Assertions.assertEquals(socks.getQuantity(), socksFromBD.getQuantity());


    }

    @Test
    void addSocks() {
        Socks socks = new Socks();
        socks.setQuantity(10L);
        socks.setColor("green");
        socks.setCottonPart(90L);
        socksService.addSocks(socks);
        Assertions.assertEquals(socks.getQuantity(), 10L);
        Mockito.verify(socksRepository, Mockito.times(1)).save(socks);
    }

    @Test
    void reduceSocks() {
    }
}