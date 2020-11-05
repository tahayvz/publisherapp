package com.tahayvz.publisherapp.converters;

import com.tahayvz.publisherapp.commands.PublishingHouseCommand;
import com.tahayvz.publisherapp.domain.PublishingHouse;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PublishingHouseCommandToPublishingHouse implements Converter<PublishingHouseCommand, PublishingHouse> {

    @Synchronized
    @Nullable
    @Override
    public PublishingHouse convert(PublishingHouseCommand source) {
        if (source == null) {
            return null;
        }

        final PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setId(source.getId());
        publishingHouse.setName(source.getName());
        publishingHouse.setDescriptions(source.getDescriptions());

        return publishingHouse;
    }
}