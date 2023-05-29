package com.example.validator.validators;

public final class ValidatorUtils {
  public static final String REGEX_ALLOWED_GENDERS = "[M|F|U]";
  public static final String REGEX_ISO8601_DATE = "\\d{4}-\\d{2}-\\d{2}";
  public static final String REGEX_LATIN_ALPHABET_NUMBERS_COMMAS_PERIODS = "^[a-zA-Z0-9 ]+$";
  public static final String REGEX_LATIN_ALPHABET = "^[a-zA-Z]+$";
  public static final String REGEX_PHONE = "\\(\\d{3}\\)\\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}";
  public static final int DEFAULT_MAX_LENGTH = 255;
  public static final int ADDRESS_MAX_LENGTH = 255;
  public static final int NAME_MAX_LENGTH = 255;
  public static final int STATE_MAX_LENGTH = 255;
}
