package com.kebinr.navegacionjuegos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_main.*

class MainActivity : AppCompatActivity() {
    lateinit var MainFragment :MainFragment
    lateinit var JuegoEmparejamientoFragment: JuegoEmparejamientoFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}