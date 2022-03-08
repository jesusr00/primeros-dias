package com.jesusr00.primarosdas.ui.map


import org.mapsforge.core.graphics.Canvas;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.map.layer.Layer;
import org.mapsforge.map.layer.overlay.Circle;
import org.mapsforge.map.layer.overlay.Marker;

class MyLocationOverlay (
    private val marker: Marker,
    private val circle: Circle? = null
) : Layer() {

    @Synchronized
    override fun draw(boundingBox: BoundingBox?, zoomLevel: Byte, canvas: Canvas?, topLeftPoint: Point?) {
        circle?.draw(boundingBox, zoomLevel, canvas, topLeftPoint)
        marker.draw(boundingBox, zoomLevel, canvas, topLeftPoint)
    }

    protected override fun onAdd() {
        circle?.displayModel = this.displayModel
        marker.displayModel = this.displayModel
    }

    override fun onDestroy() {
        marker.onDestroy()
    }

    fun setPosition(latitude: Double, longitude: Double, accuracy: Float) {
        synchronized(this) {
            val latLong = LatLong(latitude, longitude)
            marker.latLong = latLong
            if (circle != null) {
                circle.setLatLong(latLong)
                circle.radius = accuracy
            }
            requestRedraw()
        }
    }
}