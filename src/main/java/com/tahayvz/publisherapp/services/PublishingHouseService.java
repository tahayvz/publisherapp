package com.tahayvz.publisherapp.services;

import com.tahayvz.publisherapp.commands.PublishingHouseCommand;
import com.tahayvz.publisherapp.domain.PublishingHouse;

import java.util.Set;

public interface PublishingHouseService {

    Set<PublishingHouse> getPublishingHouses();

    PublishingHouse findById(Long l);

    PublishingHouseCommand findCommandById(Long l);

    PublishingHouseCommand savePublishingHouseCommand(PublishingHouseCommand command);

    void deleteById(Long idToDelete);

    PublishingHouse findByName(String Name);

}