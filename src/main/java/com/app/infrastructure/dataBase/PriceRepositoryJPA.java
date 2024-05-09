package com.app.infrastructure.dataBase;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepositoryJPA extends JpaRepository<PriceDTO, Long> {

}
