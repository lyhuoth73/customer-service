package com.lyhuoth.customerservice.customer.dto;

import lombok.Builder;

@Builder
public record CustomerResponse(
        String customerNumber,
        String firstname,
        String lastName,
        String email
) {
}
