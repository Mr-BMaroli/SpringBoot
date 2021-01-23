package com.OlaElectric.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class MqttConfiguration extends Configuration {

    private String userName;

    private String passWord;

}
