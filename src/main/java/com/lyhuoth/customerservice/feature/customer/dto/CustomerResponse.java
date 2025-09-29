package com.lyhuoth.customerservice.feature.customer.dto;

import lombok.Builder;

@Builder
public record CustomerResponse(
        String customerNumber,
        String firstname,
        String lastName,
        String email
) {
}
