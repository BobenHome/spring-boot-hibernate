package com.leslie.tung;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dongliangliang
 * @date 2023/3/31 23:30:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People {

    private Long id;

    private String name;

    private Integer age;
}
