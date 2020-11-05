package com.tahayvz.publisherapp.services;

import com.tahayvz.publisherapp.commands.PublishingHouseCommand;
import com.tahayvz.publisherapp.converters.PublishingHouseCommandToPublishingHouse;
import com.tahayvz.publisherapp.converters.PublishingHouseToPublishingHouseCommand;
import com.tahayvz.publisherapp.domain.PublishingHouse;
import com.tahayvz.publisherapp.repositories.PublishingHouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class PublishingHouseServiceImpl implements PublishingHouseService {

    private final PublishingHouseRepository publishingHouseRepository;
    private final PublishingHouseCommandToPublishingHouse publishingHouseCommandToPublishingHouse;
    private final PublishingHouseToPublishingHouseCommand publishingHouseToPublishingHouseCommand;

    public PublishingHouseServiceImpl(PublishingHouseRepository publishingHouseRepository, PublishingHouseCommandToPublishingHouse publishingHouseCommandToPublishingHouse, PublishingHouseToPublishingHouseCommand publishingHouseToPublishingHouseCommand) {
        this.publishingHouseRepository = publishingHouseRepository;
        this.publishingHouseCommandToPublishingHouse = publishingHouseCommandToPublishingHouse;
        this.publishingHouseToPublishingHouseCommand = publishingHouseToPublishingHouseCommand;
    }

    @Override
    public Set<PublishingHouse> getPublishingHouses() {
        log.debug("I'm in the service");

        Set<PublishingHouse> publishingHouseSet = new HashSet<>();
        publishingHouseRepository.findAll().iterator().forEachRemaining(publishingHouseSet::add);
        return publishingHouseSet;
    }

    @Override
    public PublishingHouse findById(Long l) {

        Optional<PublishingHouse> publishingHouseOptional = publishingHouseRepository.findById(l);

        if (!publishingHouseOptional.isPresent()) {
            throw new RuntimeException("PublishingHouse Not Found. For Id value: "+ l.toString());
        }

        return publishingHouseOptional.get();
    }

    @Override
    @Transactional
    public PublishingHouseCommand findCommandById(Long l) {
        return publishingHouseToPublishingHouseCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public PublishingHouseCommand savePublishingHouseCommand(PublishingHouseCommand command) {
        PublishingHouse detachedPublishingHouse = publishingHouseCommandToPublishingHouse.convert(command);

        PublishingHouse savedPublishingHouse = publishingHouseRepository.save(detachedPublishingHouse);
        log.debug("Saved PublishingHouseId:" + savedPublishingHouse.getId());
        return publishingHouseToPublishingHouseCommand.convert(savedPublishingHouse);
    }

    @Override
    public void deleteById(Long idToDelete) {
        publishingHouseRepository.deleteById(idToDelete);
    }

    @Override
    public PublishingHouse findByName(String name) {
        return publishingHouseRepository.findByName(name);
    }
}