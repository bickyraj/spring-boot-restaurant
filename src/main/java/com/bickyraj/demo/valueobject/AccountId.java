package com.bickyraj.demo.valueobject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(of = "accountId")
public final class AccountId {
    private final String accountId;
}
