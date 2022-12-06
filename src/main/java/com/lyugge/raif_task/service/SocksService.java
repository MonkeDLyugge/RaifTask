package com.lyugge.raif_task.service;

import com.lyugge.raif_task.domain.Socks;
import com.lyugge.raif_task.enums.Operations;
import com.lyugge.raif_task.exceptions.IncorrectParametersException;
import com.lyugge.raif_task.exceptions.NullResultException;
import com.lyugge.raif_task.repos.SocksRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocksService implements ISocksService {
    private final SocksRepository socksRepository;

    @Override
    public @NonNull Socks getByColorAndCottonPart(@NonNull String color, @NonNull Operations operation, @NonNull Long cottonPart) {
        switch (operation) {
            case moreThan:
                return socksRepository.getSocksByColorAndCottonPartIsGreaterThan(color, cottonPart).orElseThrow(() -> new NullResultException("Nothing was found for this query"));
            case lessThan:
                return socksRepository.getSocksByColorAndCottonPartLessThan(color, cottonPart).orElseThrow(() -> new NullResultException("Nothing was found for this query"));
            case equals:
                return socksRepository.getSocksByColorAndCottonPartEquals(color, cottonPart).orElseThrow(() -> new NullResultException("Nothing was found for this query"));
            default:
                throw new IncorrectParametersException("Incorrect parameters in url"); // заглушка для компилятора

        }
    }

    @Override
    public void addSocks(@NonNull Socks socks) {
        if (socksRepository.getSocksByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).isEmpty()) {
            socksRepository.save(socks);
        } else {
            Socks socksInBD = socksRepository.getSocksByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).get();
            socksInBD.setQuantity(socks.getQuantity() + socksInBD.getQuantity());
            socksRepository.save(socksInBD);
        }
    }

    @Override
    public void reduceSocks(@NonNull Socks socks) {
        if (socksRepository.getSocksByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).isPresent()) {
            Socks socksInBD = socksRepository.getSocksByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).get();
            socksInBD.setQuantity(Math.max(socks.getQuantity() - socksInBD.getQuantity(), 0));
            socksRepository.save(socksInBD);
        }
    }
}
