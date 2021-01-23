package com.OlaElectric.Configuration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KafkaConfiguration extends Configuration {

    private String stringSerialiser;

    private String byteArraySerialiser;

    private String groupID;
}
