package com.josuerdx.appsordomudos.widget.receiver

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.josuerdx.appsordomudos.widget.content.AgregarGestoWidget

class AgregarGestoWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = AgregarGestoWidget()
}