package com.jamesstonedeveloper.themeykotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import com.jamesstonedeveloper.themey.Themey
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var btnDay: Button
    private lateinit var btnNight: Button
    private lateinit var btnToggle: Button
    private lateinit var iBlue: ImageView
    private lateinit var iGreen: ImageView
    private lateinit var iRed: ImageView
    private lateinit var switchInwards: Switch
    private lateinit var switchOutwards: Switch
    private lateinit var switchNone: Switch
    private lateinit var seekBar: SeekBar
    private lateinit var tvAnimationSpeed: TextView
    private var circleAnimation: Themey.CircleAnimation = Themey.CircleAnimation.INWARD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Themey.instance.delayedInit(this, true)
        setContentView(R.layout.activity_main)
        Themey.instance.setRootLayout(findViewById(R.id.mainLayout))
        Themey.instance.defaultTheme = R.style.AppTheme

        btnDay = findViewById(R.id.btnDay)
        btnNight = findViewById(R.id.btnNight)
        btnToggle = findViewById(R.id.btnToggle)
        iBlue = findViewById(R.id.iBlue)
        iGreen = findViewById(R.id.iGreen)
        iRed = findViewById(R.id.iRed)
        switchInwards = findViewById(R.id.switchInwards)
        switchOutwards = findViewById(R.id.switchOutwards)
        switchNone = findViewById(R.id.switchNone)
        seekBar = findViewById(R.id.seekBar)
        tvAnimationSpeed = findViewById(R.id.tvAnimationSpeed)

        btnDay.setOnClickListener {
            Themey.instance.changeTheme(
                AppCompatDelegate.MODE_NIGHT_NO,
                circleAnimation
            )
        }
        btnNight.setOnClickListener {
            Themey.instance.changeTheme(
                AppCompatDelegate.MODE_NIGHT_YES,
                circleAnimation
            )
        }
        btnToggle.setOnClickListener { Themey.instance.toggleDayNight(circleAnimation) }
        iBlue.setOnClickListener {
            Themey.instance.changeTheme(
                R.style.BlueTheme,
                circleAnimation,
                (iBlue.x + (iBlue.width / 2)).roundToInt(),
                (mainLayout.height - (iBlue.height / 2))
            )
        }
        iGreen.setOnClickListener {
            Themey.instance.changeTheme(
                R.style.GreenTheme,
                circleAnimation,
                (iGreen.x + (iGreen.width / 2)).roundToInt(),
                (mainLayout.height - (iGreen.height / 2))
            )
        }
        iRed.setOnClickListener {
            Themey.instance.changeTheme(
                R.style.RedTheme,
                circleAnimation,
                (iRed.x + (iRed.width / 2)).roundToInt(),
                (mainLayout.height - (iRed.height / 2))
            )
        }

        switchInwards.setOnCheckedChangeListener { view, isChecked ->
            if (isChecked) {
                switchInwards.isEnabled = false
                switchOutwards.isEnabled = true
                switchNone.isEnabled = true

                switchOutwards.isChecked = false
                switchNone.isChecked = false

                circleAnimation = Themey.CircleAnimation.INWARD
            }
        }

        switchOutwards.setOnCheckedChangeListener { view, isChecked ->
            if (isChecked) {
                switchOutwards.isEnabled = false
                switchInwards.isEnabled = true
                switchNone.isEnabled = true

                switchInwards.isChecked = false
                switchNone.isChecked = false

                circleAnimation = Themey.CircleAnimation.OUTWARD
            }
        }

        switchNone.setOnCheckedChangeListener { view, isChecked ->
            if (isChecked) {
                switchNone.isEnabled = false
                switchOutwards.isEnabled = true
                switchInwards.isEnabled = true

                switchInwards.isChecked = false
                switchOutwards.isChecked = false

                circleAnimation = Themey.CircleAnimation.NONE
            }
        }

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Themey.instance.animationDuration = progress.toLong()
                tvAnimationSpeed.text = "Animation speed: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }

            override fun onStopTrackingTouch(seekBar: SeekBar?) { }


        })

    }
}
