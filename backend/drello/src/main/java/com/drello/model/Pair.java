package com.drello.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<T, J> {
    private T first;
    private J second;
}
