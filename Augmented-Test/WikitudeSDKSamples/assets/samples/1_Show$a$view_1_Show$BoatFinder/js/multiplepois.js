
// implementation of AR-Experience (aka "World")
var World = {
	// true once data was fetched
	initiallyLoadedData: false,

	// different POI-Marker assets
	markerDrawable_idle: null,

	// list of AR.GeoObjects that are currently shown in the scene / World
	markerList: [],

	// The last selected marker
	currentMarker: null,

	// called to inject new POI data
	loadPoisFromJsonData: function loadPoisFromJsonDataFn(poiData) {
		// empty list of visible markers
		World.markerList = [];

		// start loading marker assets
		World.markerDrawable_idle = new AR.ImageResource("assets/boatinfo.png");

		// loop through POI-information and create an AR.GeoObject (=Marker) per POI
		for (var currentPlaceNr = 0; currentPlaceNr < poiData.length; currentPlaceNr++) {
			var singlePoi = {
				"id": poiData[currentPlaceNr].id,
				"latitude": parseFloat(poiData[currentPlaceNr].latitude),
				"longitude": parseFloat(poiData[currentPlaceNr].longitude),
				"altitude": parseFloat(poiData[currentPlaceNr].altitude),
				"title": poiData[currentPlaceNr].name,
				"description": poiData[currentPlaceNr].description
			};

			/*
				To be able to deselect a marker while the user taps on the empty screen, 
				the World object holds an array that contains each marker.
			*/
			World.markerList.push(new Marker(singlePoi));
		}

	},

	// location updates, fired every time you call architectView.setLocation() in native environment
	locationChanged: function locationChangedFn(lat, lon, alt, acc) {

		/*
			The custom function World.onLocationChanged checks with the flag World.initiallyLoadedData if the function was already called. With the first call of World.onLocationChanged an object that contains geo information will be created which will be later used to create a marker using the World.loadPoisFromJsonData function.
		*/
		if (!World.initiallyLoadedData) {
			/* 
				requestDataFromLocal with the geo information as parameters (latitude, longitude) creates different poi data to a random location in the user's vicinity.
			*/
			World.requestDataFromLocal(lat, lon);
			World.initiallyLoadedData = true;
		}
	},

	// request POI data
	requestDataFromLocal: function requestDataFromLocalFn(centerPointLatitude, centerPointLongitude) {

		/*
		var id = 1;
		var latitude = 51.9176436;
		var longitude = 4.4823579;
		var description = "Een lange beschrijving";
		var altitude = "100.0";
		var name = "Maritiem Museum";	
		
		var ShipData = [];

			ShipData.push({
				"id": id,
				"longitude": (longitude),
				"latitude": (latitude),
				"description": (description),
				"altitude": altitude,
				"name": (name)
			}); */

			var ShipData = [];

//testdata havenbedrijf			

			ShipData.push({
				"id": 1,
				"longitude": (4.526599),
				"latitude": (51.911090),
				"description": ("8 knots - 20 degrees NW"),
				"altitude": "100.0",
				"name": ("Rocket")
			});
			
			ShipData.push({
				"id": 2,
				"longitude": (4.530135),
				"latitude": (51.909978),
				"description": ("7 knots - 2 degrees NE"),
				"altitude": "100.0",
				"name": ("Queen Victoria")
			});
			
			ShipData.push({
				"id": 3,
				"longitude": (4.524827),
				"latitude": (51.908620),
				"description": ("8 knots - 10 degrees NW"),
				"altitude": "100.0",
				"name": ("Seaturtle")
			});
			
			ShipData.push({
				"id": 4,
				"longitude": (4.528140),
				"latitude": (51.908080),
				"description": ("7 knots - 5 degrees NE"),
				"altitude": "100.0",
				"name": ("Tucker")
			});

	/*
			ShipData.push({
				"id": 1,
				"longitude": (4.482340),
				"latitude": (51.917695),
				"description": ("Dit is schip 1"),
				"altitude": "100.0",
				"name": ("Rocket")
			});
			
			ShipData.push({
				"id": 2,
				"longitude": (4.485580),
				"latitude": (51.918441),
				"description": ("Dit is schip 2"),
				"altitude": "100.0",
				"name": ("Queen Victoria")
			});
			
			ShipData.push({
				"id": 3,
				"longitude": (4.483428),
				"latitude": (51.916111),
				"description": ("Dit is schip 3"),
				"altitude": "100.0",
				"name": ("Seaturtle")
			});
			
			ShipData.push({
				"id": 4,
				"longitude": (4.486911),
				"latitude": (51.916871),
				"description": ("Dit is schip 4"),
				"altitude": "100.0",
				"name": ("Carl")
			});
*/
		
		World.loadPoisFromJsonData(ShipData);
	}

};

/* 
	Set a custom function where location changes are forwarded to. There is also a possibility to set AR.context.onLocationChanged to null. In this case the function will not be called anymore and no further location updates will be received. 
*/
AR.context.onLocationChanged = World.locationChanged;