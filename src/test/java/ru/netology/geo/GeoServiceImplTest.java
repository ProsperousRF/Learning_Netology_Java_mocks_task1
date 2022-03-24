package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

/**
 * @author Stanislav Rakitov in 2022
 */
class GeoServiceImplTest {

  static GeoService service = new GeoServiceImpl();
  static Country country = Country.RUSSIA;

  @Test
  @DisplayName("Russian IP")
  void moscowIp() {
    Location location = service.byIp("172.0.32.11");
    Assertions.assertEquals(country, location.getCountry());
  }

  @Test
  @DisplayName("Foreign IP")
  void foreignIp() {
    Location location = service.byIp("96.0.0.1");
    Assertions.assertNotEquals(country, location.getCountry());
  }

}