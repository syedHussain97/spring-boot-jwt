package com.hussain.securewebcrawler.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;

@Value.Immutable
@Value.Style(allParameters = true,
        visibility = Value.Style.ImplementationVisibility.PUBLIC,
        typeImmutable = "User")
@JsonSerialize(as = User.class)
@JsonDeserialize(as = User.class)
public interface UserType {

    @NotNull
    String username();

    @NotNull
    String password();
}