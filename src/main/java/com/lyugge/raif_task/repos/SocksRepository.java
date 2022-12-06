package com.lyugge.raif_task.repos;

import com.lyugge.raif_task.domain.Socks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SocksRepository extends JpaRepository<Socks, Long> {
    Optional<Socks> getSocksByColorAndCottonPartLessThan(String color, Long cottonPart);
    Optional<Socks> getSocksByColorAndCottonPartEquals(String color, Long cottonPart);
    Optional<Socks> getSocksByColorAndCottonPartIsGreaterThan(String color, Long cottonPart);
}
