// MqttModule.kt
package com.josuerdx.appsordomudos.model.service.mqtt

import android.content.Context

object MqttModule {
    fun provideMqttClientHelper(context: Context): MqttClientHelper {
        return MqttClientHelper(context)
    }
}