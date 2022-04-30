package org.adaschool.tdd.repository.document;

public class GeoLocation
{

    private final double lat;

    private final double lng;

    public GeoLocation( double lat, double lng )
    {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat()
    {
        return lat;
    }

    public double getLng()
    {
        return lng;
    }

    public double distance(GeoLocation othGeoLoc) {
        double hick1 = this.lat - othGeoLoc.getLat();
        double hick2 = this.lng - othGeoLoc.getLng();
        double hypotenuse = Math.sqrt(hick1*hick1 + hick2*hick2);
        return hypotenuse;

    }
}
