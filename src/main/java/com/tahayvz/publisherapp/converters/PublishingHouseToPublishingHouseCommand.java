package com.tahayvz.publisherapp.converters;


import com.tahayvz.publisherapp.commands.PublishingHouseCommand;
import com.tahayvz.publisherapp.domain.PublishingHouse;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PublishingHouseToPublishingHouseCommand implements Converter<PublishingHouse, PublishingHouseCommand>{

    @Synchronized
    @Nullable
    @Override
    public PublishingHouseCommand convert(PublishingHouse source) {
        if (source == null) {
            return null;
        }

        final PublishingHouseCommand command = new PublishingHouseCommand();
        command.setId(source.getId());
        command.setName(source.getName());
        command.setDescriptions(source.getDescriptions());

        return command;
    }
}