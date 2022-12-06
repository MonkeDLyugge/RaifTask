package com.lyugge.raif_task.controller;

import com.lyugge.raif_task.domain.Socks;
import com.lyugge.raif_task.enums.Operations;
import com.lyugge.raif_task.service.ISocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SocksController {

    private final ISocksService iSocksService;

    @GetMapping(value = "api/socks")
    public Socks getByColorAndCottonPart(@RequestParam(value = "color") final String color,
                                         @RequestParam(value = "operation") final Operations operation,
                                         @RequestParam(value = "cottonPart") final Long cottonPart){
        return iSocksService.getByColorAndCottonPart(color, operation, cottonPart);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "api/socks/income")
    public void addSocks(@RequestBody Socks socks){
        iSocksService.addSocks(socks);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "api/socks/outcome")
    public void reduceSocks(@RequestBody Socks socks){
        iSocksService.reduceSocks(socks);
    }
}
