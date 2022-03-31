package com.jesusr00.primarosdas.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.jesusr00.primarosdas.R
import com.jesusr00.primarosdas.databinding.FragmentMapBinding
import com.jesusr00.primarosdas.utils.CopyAssets
import org.mapsforge.core.graphics.Bitmap
import org.mapsforge.core.model.LatLong
import org.mapsforge.map.android.graphics.AndroidBitmap
import org.mapsforge.map.android.graphics.AndroidGraphicFactory
import org.mapsforge.map.android.util.AndroidUtil
import org.mapsforge.map.android.view.MapView
import org.mapsforge.map.datastore.MapDataStore
import org.mapsforge.map.layer.cache.TileCache
import org.mapsforge.map.layer.overlay.Circle
import org.mapsforge.map.layer.overlay.Marker
import org.mapsforge.map.layer.renderer.TileRendererLayer
import org.mapsforge.map.reader.MapFile
import org.mapsforge.map.rendertheme.InternalRenderTheme
import java.io.File


class MapFragment : Fragment(){

    private lateinit var binding: FragmentMapBinding
    private lateinit var tileRendererLayer: TileRendererLayer
    private lateinit var mapView: MapView
    private lateinit var tileCache: TileCache
    private lateinit var fileMap: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidGraphicFactory.createInstance(requireActivity().application)
        fileMap = File(requireContext().getExternalFilesDir("maps"), "uci.map")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!fileMap.exists()) {
            CopyAssets(requireContext()).copyAssetsMap()
            fileMap = File(requireContext().getExternalFilesDir("maps"), "uci.map")
        }
        binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapDataStore: MapDataStore = MapFile(fileMap)

        mapView = binding.mapView
        mapView.isClickable = true
        mapView.mapScaleBar.isVisible = false
        mapView.setBuiltInZoomControls(true)
        mapView.mapZoomControls.zoomLevelMin = 10.toByte()
        mapView.mapZoomControls.zoomLevelMax = 20.toByte()

        // create a tile cache of suitable count
        tileCache = AndroidUtil.createTileCache(
            context, "mapcache",
            mapView.model.displayModel.tileSize, 1f,
            mapView.model.frameBufferModel.overdrawFactor
        )

        mapView.model.mapViewPosition.center = LatLong(22.9881563, -82.4648623)
        mapView.model.mapViewPosition.zoomLevel = 15.toByte()
        mapView.mapZoomControls.zoomLevelMin = 15.toByte()
        mapView.mapZoomControls.zoomLevelMax = 24.toByte()

        tileRendererLayer = TileRendererLayer(
            tileCache, mapDataStore,
            mapView.model.mapViewPosition,
            false,
            true,
            true,
            AndroidGraphicFactory.INSTANCE
        )
        tileRendererLayer.setXmlRenderTheme(InternalRenderTheme.OSMARENDER)

        mapView.layerManager.layers.add(tileRendererLayer)
    }

}
