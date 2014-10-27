function Marker(poiData) {

    /*
        For creating the marker a new object AR.GeoObject will be created at the specified geolocation. An AR.GeoObject connects one or more AR.GeoLocations with multiple AR.Drawables. The AR.Drawables can be defined for multiple targets. A target can be the camera, the radar or a direction indicator. Both the radar and direction indicators will be covered in more detail in later examples.
    */

    this.poiData = poiData;

    // create the AR.GeoLocation from the poi data
    var markerLocation = new AR.GeoLocation(poiData.latitude, poiData.longitude, poiData.altitude);

    // create an AR.ImageDrawable for the marker in idle state
    this.markerDrawable_idle = new AR.ImageDrawable(World.markerDrawable_idle, 2.5, {
        zOrder: 0,
        opacity: 1.0,
        /*
            To react on user interaction, an onClick property can be set for each AR.Drawable. The property is a function which will be called each time the user taps on the drawable. The function called on each tap is returned from the following helper function defined in marker.js. The function returns a function which checks the selected state with the help of the variable isSelected and executes the appropriate function. The clicked marker is passed as an argument.
        */
        
    });

    // create an AR.Label for the marker's title 
    this.titleLabel = new AR.Label(poiData.title.trunc(45), 1, {
        zOrder: 1,
        offsetY: 0.55,
        style: {
            textColor: '#FFFFFF',
            fontStyle: AR.CONST.FONT_STYLE.BOLD
        }
    });

    // create an AR.Label for the marker's description
    this.descriptionLabel = new AR.Label(poiData.description.trunc(100), 0.8, {
        zOrder: 1,
        offsetY: -0.55,
        style: {
            textColor: '#FFFFFF'
        }
    });

    // create the AR.GeoObject with the drawable objects
    this.markerObject = new AR.GeoObject(markerLocation, {
        drawables: {
            cam: [this.markerDrawable_idle, this.titleLabel, this.descriptionLabel]
        }
    });

    return this;
}

Marker.prototype.setDeselected = function(marker) {

    marker.isSelected = false;

    marker.markerDrawable_idle.opacity = 1.0;
    marker.markerDrawable_selected.opacity = 0.0;

    marker.markerDrawable_idle.onClick = Marker.prototype.getOnClickTrigger(marker);
    marker.markerDrawable_selected.onClick = null;
};

// will truncate all strings longer than given max-length "n". e.g. "foobar".trunc(3) -> "foo..."
String.prototype.trunc = function(n) {
    return this.substr(0, n - 1) + (this.length > n ? '...' : '');
};