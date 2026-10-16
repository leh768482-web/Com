package com.vnm.autogesture

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    private lateinit var info: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pad = (20 * resources.displayMetrics.density).toInt()

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(pad, pad * 2, pad, pad)
        }
        val title = TextView(this).apply {
            text = "Auto Gesture"
            textSize = 26f
        }
        info = TextView(this).apply {
            textSize = 16f
            setPadding(0, pad, 0, pad)
        }
        val b1 = Button(this).apply {
            text = "1. Mở cài đặt Trợ năng"
            setOnClickListener { startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)) }
        }
        val b2 = Button(this).apply {
            text = "2. Hiện bảng điều khiển nổi"
            setOnClickListener { GestureService.instance?.showPanel() }
        }
        val help = TextView(this).apply {
            text = "Cách dùng:\n" +
                "• Trong Trợ năng > Ứng dụng đã cài đặt, bật \"Auto Gesture\".\n" +
                "• Bảng nổi hiện lên: bấm ● Ghi, rồi vuốt/chạm bình thường (màn hình hơi đỏ là đang ghi).\n" +
                "• Để im vài giây rồi bấm ■ Lưu.\n" +
                "• Bấm ▶ Phát. Chọn \"1 lần\" hoặc \"∞ Lặp\". \"Không trễ\" = chạy liên tục, \"Giữ nhịp\" = giữ khoảng nghỉ lúc ghi.\n" +
                "• Đang phát vẫn bấm màn hình bình thường được. Kéo ☰ để dời bảng, ✕ để ẩn."
            textSize = 14f
            setPadding(0, pad, 0, 0)
        }
        root.addView(title)
        root.addView(info)
        root.addView(b1)
        root.addView(b2)
        root.addView(help)
        setContentView(root)
    }

    override fun onResume() {
        super.onResume()
        info.text = if (GestureService.instance != null)
            "✅ Dịch vụ đang bật"
        else
            "❌ Dịch vụ chưa bật. Android 13+: nếu bị chặn, vào Cài đặt > Ứng dụng > Auto Gesture > ⋮ > Cho phép cài đặt bị hạn chế."
    }
}
