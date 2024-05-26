package com.leslie.tung.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dongliangliang
 * @date 2023/3/20 17:56:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerData {

    private String firstName;
    private String lastName;
    private String email;
}
