/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.fr4gus.electronappengine.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "location",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.electronappengine.fr4gus.example.com",
                ownerName = "backend.electronappengine.fr4gus.example.com",
                packagePath = ""
        )
)
public class LocationEndpoint {
    private static final Logger log = Logger.getLogger(LocationEndpoint.class.getName());

    static List<LocationFix> mFixed = new ArrayList<>();

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "add")
    public void add(@Named("fix") String fix) {
        String[] latlon = fix.split(",");

        log.info("Received: " + fix);

        LocationFix locationFix = new LocationFix();
        locationFix.setCreatedAt(new Date());
        locationFix.setLatitude(Double.parseDouble(latlon[0]));
        locationFix.setLongitude(Double.parseDouble(latlon[1]));

        mFixed.add(locationFix);
    }

    @ApiMethod(name = "list", httpMethod = "GET")

    public CollectionResponse<LocationFix> list(@Named("count") int count) {
        log.info("Listing fixes");

        return CollectionResponse.<LocationFix>builder().setItems(mFixed).build();
    }

}
