package com.senin.medyasesi

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.accessibility.AccessibilityEvent

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
    
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}

    // Navigasyon barındaki ikona tıklandığında çalışır
    override fun onAccessibilityButtonClicked() {
        super.onAccessibilityButtonClicked()
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        
        // Ekrana hiçbir menü açmadan, sadece orjinal ses barını çağırır (Video kesilmez)
        audioManager.adjustStreamVolume(
            AudioManager.STREAM_MUSIC, 
            AudioManager.ADJUST_SAME, 
            AudioManager.FLAG_SHOW_UI
        )
    }
}
