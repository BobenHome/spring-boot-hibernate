package com.leslie.tung.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:configuration.properties")
@Getter
@Setter
public class ConfigurationProperties {

    @Value("${zxy.class.interfaces.classInfo}")
    private String interfaceClassInfo;

    @Value("${zxy.class.interfaces.classActivity}")
    private String interfaceClassActivity;

    @Value("${zxy.class.interfaces.classStudents}")
    private String interfaceClassStudents;

    @Value("${zxy.class.appId}")
    private String zxyClassAppId;

    @Value("${zxy.class.syncLookScopeHour}")
    private int syncLookScopeHour;

    @Value("${zxy.class.page}")
    private int page;

    @Value("${zxy.class.pageSize}")
    private int pageSize;

    @Value("${zxy.class.batchSize}")
    private int batchSize;
}
