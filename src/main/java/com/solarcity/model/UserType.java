package com.solarcity.model;


import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;

@Value.Immutable
@Value.Style(allParameters = true,
        visibility = Value.Style.ImplementationVisibility.PUBLIC,
        typeImmutable = "User")
public interface UserType {

    @NotNull
    String username();

    @NotNull
    String password();
}