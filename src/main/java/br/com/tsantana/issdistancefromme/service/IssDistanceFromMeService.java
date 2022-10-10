package br.com.tsantana.issdistancefromme.service;

import br.com.tsantana.issdistancefromme.client.GeoJsClient;
import br.com.tsantana.issdistancefromme.client.IssHttpClient;
import br.com.tsantana.issdistancefromme.client.vo.IssGeolocation;
import br.com.tsantana.issdistancefromme.client.vo.UserGeoLocation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssDistanceFromMeService {
    static final double EQUATORIAL_EARTH_RADIUS = 6378.1370D;
    static final double D_2_R = (Math.PI / 180D);

    private final IssHttpClient issHttpClient;
    private final GeoJsClient geoJsClient;

    public double getIssDistance(String clientIP) {

        var issLocationMono = issHttpClient.getIssGeolocation();
        var userGeoLocationMono = geoJsClient.getIssGeolocation(clientIP);

        IssGeolocation issGeolocation = issLocationMono.block();
        UserGeoLocation userGeoLocation = userGeoLocationMono.block();

        return haversineInKM(
                Double.parseDouble(issGeolocation.position().latitude()),
                Double.parseDouble(issGeolocation.position().longitude()),
                Double.parseDouble(userGeoLocation.latitude()),
                Double.parseDouble(userGeoLocation.longitude()));
    }

    public double haversineInKM(double lat1, double long1, double lat2, double long2) {
        double dLong = (long2 - long1) * D_2_R;
        double dLat = (lat2 - lat1) * D_2_R;
        double a = Math.pow(Math.sin(dLat / 2D), 2D) + Math.cos(lat1 * D_2_R) * Math.cos(lat2 * D_2_R)
                * Math.pow(Math.sin(dLong / 2D), 2D);
        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));

        return EQUATORIAL_EARTH_RADIUS * c;
    }
}
