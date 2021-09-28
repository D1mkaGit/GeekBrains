package ru.geekbrains;

import ru.geekbrains.dto.ProductRemoteDto;

import java.util.List;

public interface ProductServiceRemote {

    List<ProductRemoteDto> findAllRemote();
}
