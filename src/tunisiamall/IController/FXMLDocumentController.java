package tunisiamall.IController;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tunisiamall.main.MainRespE;

public class FXMLDocumentController implements Initializable, MapComponentInitializedListener {

    @FXML
    private Button button;

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {
        LatLong tunisiaMall = new LatLong(36.850501, 10.273087);
        LatLong me = new LatLong(47.6297, -122.3431);

        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(36.850501, 10.273087))
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);

        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(tunisiaMall);

        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(me);

        Marker tunisiaMallMarker = new Marker(markerOptions1);
        Marker meMarker = new Marker(markerOptions2);

        map.addMarker(tunisiaMallMarker);
        map.addMarker(meMarker);

        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        InfoWindow tunisiaMallInfoWindow = new InfoWindow(infoWindowOptions);
        tunisiaMallInfoWindow.setContent("Tunisia Mall");
        tunisiaMallInfoWindow.open(map, tunisiaMallMarker);
    }
    private Stage stage;
    private MainRespE main;

    public void setMain(MainRespE main, Stage stage) {
        this.main = main;
        this.stage = stage;
    }

}
