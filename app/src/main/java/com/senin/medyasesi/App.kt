package com.senin.medyasesi

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityButtonController
import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.accessibility.AccessibilityEvent
import android.os.Build

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)
        textView.text = "Kurulum Tamamlandı!\n\nLütfen telefonunuzun Ayarlar > Ek Ayarlar > Erişilebilirlik menüsüne gidin. 'İndirilen Uygulamalar' bölümünden 'Medya Sesi'ni aktif edin. Navigasyon barınıza tuş eklenecektir."
        textView.textSize = 18f
        textView.setPadding(50, 50, 50, 50)
        setContentView(textView)
    }
}

class VolumeAccessibilityService : AccessibilityService() {

    // Servis sisteme bağlandığında butonu dinlemeye başlar
    override fun onServiceConnected() {
        super.onServiceConnected()
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val controller = accessibilityButtonController
            val callback = object : AccessibilityButtonController.AccessibilityButtonCallback() {
                // Navigasyon barındaki ikona tıklandığında çalışır
                override fun onClicked(controller: AccessibilityButtonController) {
                    val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
                    audioManager.adjustStreamVolume(
                        AudioManager.STREAM_MUSIC, 
                        AudioManager.ADJUST_SAME, 
                        AudioManager.FLAG_SHOW_UI
                    )
                }
            }
            controller.registerAccessibilityButtonCallback(callback)
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}
}
