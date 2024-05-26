package com.leslie.tung.util;

import io.hypersistence.tsid.TSID;

import java.util.function.Supplier;

/**
 * @author dongliangliang
 * @date 2023/3/20 21:04:29
 * @description 自定义TSID 生成器
 */
public class CustomTsidSupplier implements Supplier<TSID.Factory> {
    @Override
    public TSID.Factory get() {
        return TSID.Factory.builder()
                .withNodeBits(2)
                .build();
    }
}
