package com.solarcity.model;

import com.google.common.base.MoreObjects;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link UserType}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code User.builder()}.
 * Use the static factory method to create immutable instances:
 * {@code User.of()}.
 */
@Generated(from = "UserType", generator = "Immutables")
@SuppressWarnings({"all"})
@ParametersAreNonnullByDefault
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
@Immutable
@CheckReturnValue
public final class User implements UserType {
  private final String username;
  private final String password;

  private User(String username, String password) {
    this.username = Objects.requireNonNull(username, "username");
    this.password = Objects.requireNonNull(password, "password");
  }

  private User(User original, String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * @return The value of the {@code username} attribute
   */
  @Override
  public String username() {
    return username;
  }

  /**
   * @return The value of the {@code password} attribute
   */
  @Override
  public String password() {
    return password;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link UserType#username() username} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for username
   * @return A modified copy of the {@code this} object
   */
  public final User withUsername(String value) {
    String newValue = Objects.requireNonNull(value, "username");
    if (this.username.equals(newValue)) return this;
    return new User(this, newValue, this.password);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link UserType#password() password} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for password
   * @return A modified copy of the {@code this} object
   */
  public final User withPassword(String value) {
    String newValue = Objects.requireNonNull(value, "password");
    if (this.password.equals(newValue)) return this;
    return new User(this, this.username, newValue);
  }

  /**
   * This instance is equal to all instances of {@code User} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof User
        && equalTo((User) another);
  }

  private boolean equalTo(User another) {
    return username.equals(another.username)
        && password.equals(another.password);
  }

  /**
   * Computes a hash code from attributes: {@code username}, {@code password}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    @Var int h = 5381;
    h += (h << 5) + username.hashCode();
    h += (h << 5) + password.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code UserType} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("UserType")
        .omitNullValues()
        .add("username", username)
        .add("password", password)
        .toString();
  }

  /**
   * Construct a new immutable {@code UserType} instance.
   * @param username The value for the {@code username} attribute
   * @param password The value for the {@code password} attribute
   * @return An immutable UserType instance
   */
  public static User of(String username, String password) {
    return new User(username, password);
  }

  /**
   * Creates an immutable copy of a {@link UserType} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable UserType instance
   */
  public static User copyOf(UserType instance) {
    if (instance instanceof User) {
      return (User) instance;
    }
    return User.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link User User}.
   * <pre>
   * User.builder()
   *    .username(String) // required {@link UserType#username() username}
   *    .password(String) // required {@link UserType#password() password}
   *    .build();
   * </pre>
   * @return A new User builder
   */
  public static User.Builder builder() {
    return new User.Builder();
  }

  /**
   * Builds instances of type {@link User User}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "UserType", generator = "Immutables")
  @NotThreadSafe
  public static final class Builder {
    private static final long INIT_BIT_USERNAME = 0x1L;
    private static final long INIT_BIT_PASSWORD = 0x2L;
    private long initBits = 0x3L;

    private @Nullable String username;
    private @Nullable String password;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code UserType} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder from(UserType instance) {
      Objects.requireNonNull(instance, "instance");
      username(instance.username());
      password(instance.password());
      return this;
    }

    /**
     * Initializes the value for the {@link UserType#username() username} attribute.
     * @param username The value for username 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder username(String username) {
      this.username = Objects.requireNonNull(username, "username");
      initBits &= ~INIT_BIT_USERNAME;
      return this;
    }

    /**
     * Initializes the value for the {@link UserType#password() password} attribute.
     * @param password The value for password 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder password(String password) {
      this.password = Objects.requireNonNull(password, "password");
      initBits &= ~INIT_BIT_PASSWORD;
      return this;
    }

    /**
     * Builds a new {@link User User}.
     * @return An immutable instance of UserType
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public User build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new User(null, username, password);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_USERNAME) != 0) attributes.add("username");
      if ((initBits & INIT_BIT_PASSWORD) != 0) attributes.add("password");
      return "Cannot build UserType, some of required attributes are not set " + attributes;
    }
  }
}
