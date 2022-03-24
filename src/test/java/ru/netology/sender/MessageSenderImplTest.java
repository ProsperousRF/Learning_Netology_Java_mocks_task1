package ru.netology.sender;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Stanislav Rakitov in 2022
 */
class MessageSenderImplTest {

  @Test
  @DisplayName("Russian message")
  void messageRussian() {
    GeoService geoService = Mockito.mock(GeoServiceImpl.class);
    Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
        .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

    LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
    Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

    MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
    Map<String, String> headers = new HashMap<>();
    headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.163.34.69");
    String expected = "Добро пожаловать";
    String message = messageSender.send(headers);
    assertEquals(expected, message);
  }

  @Test
  @DisplayName("English message")
  void messageEnglish() {
    GeoService geoService = Mockito.mock(GeoServiceImpl.class);
    Mockito.when(geoService.byIp(Mockito.startsWith("96.")))
            .thenReturn(new Location("New York", Country.USA, null,  0));

    LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
    Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

    MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
    Map<String, String> headers = new HashMap<>();
    headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.174.16.32");
    String expected = "Welcome";
    String message = messageSender.send(headers);
    assertEquals(expected, message);

  }
}
