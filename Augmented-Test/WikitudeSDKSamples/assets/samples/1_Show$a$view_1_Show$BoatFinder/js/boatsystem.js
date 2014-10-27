var Solar = {
    planetsInfo: null,

    init: function() {
        var distanceFactor = 580.2;

        /* null means: use relative to user, boat is NORTH to the user */
        /* inputvolgorde is = ( location , northing , easting , altitudeDelta )*/
        
        
        var geoLoc = new AR.GeoLocation(51.917255, 4.484558);
        var geoLoc2 = new AR.GeoLocation(51.916843, 4.485422);
         
        var locationBoat1 = new AR.RelativeLocation(geoLoc, 200, 20, 0);
        var locationBoat2 = new AR.RelativeLocation(geoLoc2, 100, -20, 0);

        /* every object in space has a name, location and a circle (drawable) */
        var boatImg = new AR.ImageResource("assets/boat.png");
        var indicatorImg = new AR.ImageResource("assets/indi.png");

		/*afstand van de label tov de gebruiker, de AR*/
        var boat1DistanceSize = 2;
        var boat2DistanceSize = 2;

        var boat1 = {
            name: "tugboat1",
            distance: 0,
            location: locationBoat1,
            imgDrawable: new AR.ImageDrawable(boatImg, boat1DistanceSize),
            size: boat1DistanceSize,
            description: "Ship 1 details",
            speed: "knopen",
            koers: "aantal graden"
        };
        
        var boat2 = {
            name: "tugboat2",
            distance: 0,
            location: locationBoat2,
            imgDrawable: new AR.ImageDrawable(boatImg, boat2DistanceSize),
            size: boat2DistanceSize,
            description: "Ship 2 details",
            speed: "knopen",
            koers: "aantal graden"
        };


        /* put boat(s) in an array */
        this.planetsInfo = [boat1, boat2];

        /* create helper array to create goeObjects out of given information */
        var planetsGeoObjects = [];
        for (var i = 0; i < this.planetsInfo.length; i++) {

            /* show name of object below*/
            var label = new AR.Label(this.planetsInfo[i].name, 3, {
                offsetY: -this.planetsInfo[i].size / 2,
                verticalAnchor: AR.CONST.VERTICAL_ANCHOR.TOP,
                opacity: 0.0,
                zOrder: 1,
                style: {
                    textColor: '#FFFFFF',
                    backgroundColor: '#00000005'
                }
            });

            /* drawable in cam of object -> image and label */
            var drawables = [];
            drawables[0] = this.planetsInfo[i].imgDrawable;
            drawables[1] = label;

            /* Create objects in AR*/
            planetsGeoObjects[i] = new AR.GeoObject(this.planetsInfo[i].location, {
                drawables: {
                    cam: drawables
                },
                onClick: this.planetClicked(this.planetsInfo[i])
            });
        }

        // Add indicator to boat
        var imageDrawable = new AR.ImageDrawable(indicatorImg, 0.1, {
            verticalAnchor: AR.CONST.VERTICAL_ANCHOR.TOP
        });
        planetsGeoObjects[0].drawables.addIndicatorDrawable(imageDrawable);
    },

    planetClicked: function(planet) {
        return function() {
            document.getElementById("info").setAttribute("class", "info");
            document.getElementById("name").innerHTML = planet.name;
            document.getElementById("mass").innerHTML = planet.speed;
            document.getElementById("diameter").innerHTML = planet.koers;
            document.getElementById("info").setAttribute("class", "infoVisible");
        };
    }
};

Solar.init();