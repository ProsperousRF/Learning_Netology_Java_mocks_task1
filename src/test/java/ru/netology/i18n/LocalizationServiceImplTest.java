package ru.netology.i18n;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Stanislav Rakitov in 2022
 */
class LocalizationServiceImplTest {

  private static final LocalizationService service = new LocalizationServiceImpl();

  @Test
  @DisplayName("Russian")
  void localeRussian() {
    String russian = service.locale(Country.RUSSIA);
    String expected = "Добро пожаловать";
    assertEquals(expected, russian);
  }

  @Test
  @DisplayName("Any country")
  void localeEnglish() {
    String otherCountry = service.locale(Country.GERMANY);
    String expected = "Welcome";
    assertEquals(expected, otherCountry);
  }

  @Test
  @DisplayName("USA")
  void localeUSEnglish() {
    String locaaleUSA = service.locale(Country.USA);
    String expected = "Welcome";
    assertEquals(expected, locaaleUSA);
  }
}