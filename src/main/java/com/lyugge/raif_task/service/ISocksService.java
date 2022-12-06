package com.lyugge.raif_task.service;

import com.lyugge.raif_task.domain.Socks;
import com.lyugge.raif_task.enums.Operations;
import lombok.NonNull;

public interface ISocksService {
    @NonNull
    Socks getByColorAndCottonPart(@NonNull String color, @NonNull Operations operation, @NonNull Long cottonPart);
    void addSocks(@NonNull Socks socks);
    void reduceSocks(@NonNull Socks socks);
}
