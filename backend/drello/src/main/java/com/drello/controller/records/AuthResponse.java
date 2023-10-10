package com.drello.controller.records;

import com.drello.model.Member;

public record AuthResponse(String token, Member user) {

}
