// This #include statement was automatically added by the Particle IDE.
#include "AssetTracker/AssetTracker.h"

// This is just a little example of how to user webhooks, The service we built was made for Google AppEngine
// Created by Franklin Garcia.


// Creating an AssetTracker named 't' for us to reference
AssetTracker t = AssetTracker();

// Used to keep track of the last time acceleration was checked
long lastPublish = 0;

void setup() {
    t.begin();
    t.gpsOn();
    
    // Opens up a Serial port so you can listen over USB
    Serial.begin(9600);
    Serial.println("Setup Finished");
}

void loop() {
    
    // You'll need to run this every loop to capture the GPS output
    t.updateGPS();
    
    // If it's set to transmit AND it's been at least delayMinutes since the last one...
    long timeSinceLastPublish = (millis()-lastPublish);
    
    if (t.gpsFix() && (timeSinceLastPublish > 30000) ){
        publishLocation();
    }    

}

void publishLocation() {
        String jsonData = String::format("{ \"fix\": \"%s\" }", t.readLatLon().c_str());
        Serial.println(jsonData);
        Particle.publish("SF", jsonData, 60, PRIVATE);
}