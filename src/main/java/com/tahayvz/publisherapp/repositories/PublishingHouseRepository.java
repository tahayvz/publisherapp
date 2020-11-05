package com.tahayvz.publisherapp.repositories;

import com.tahayvz.publisherapp.domain.PublishingHouse;
import org.springframework.data.repository.CrudRepository;

public interface PublishingHouseRepository extends CrudRepository<PublishingHouse, Long> {

    PublishingHouse findByName(String Name);

}
