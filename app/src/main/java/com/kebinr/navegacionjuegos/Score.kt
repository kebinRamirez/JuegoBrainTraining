package com.kebinr.navegacionjuegos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*
import androidx.fragment.app.FragmentManager as FragmentManager1


class Score : Fragment(), View.OnClickListener {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<TextView>(R.id.textView6).text = requireArguments()!!.getString("Score")
        view.findViewById<Button>(R.id.Juegomatch).setOnClickListener(this)
        view.findViewById<Button>(R.id.JuegoMemoria).setOnClickListener(this)
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        println("LLEGOOO")
    }

    override fun onClick(V: View?) {
        when(V!!.id){
            R.id.Juegomatch->{
                navController!!.navigate(R.id.action_score_to_juegoEmparejamientoFragment)
            }
            R.id.JuegoMemoria->{
                navController!!.navigate(R.id.action_score_to_juegoMemoriaFragment)
            }

        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {

            }

    }


}