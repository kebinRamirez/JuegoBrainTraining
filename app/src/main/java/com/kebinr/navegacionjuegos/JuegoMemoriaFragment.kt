package com.kebinr.navegacionjuegos

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_juego_memoria.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class JuegoMemoriaFragment : Fragment() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    val con=context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_juego_memoria, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        var n: Int = 2
        var cont: Int =0
        var lifes = 3
        var score = 0
        val buttons: Array<ImageButton> = arrayOf(imageButton,imageButton2,imageButton3,imageButton4,imageButton5,imageButton6,imageButton7,imageButton8,imageButton9,imageButton10,imageButton11,imageButton12,imageButton13,imageButton14,imageButton15,imageButton16,imageButton17,imageButton18,imageButton19,imageButton20,imageButton21,imageButton22,imageButton23,imageButton24,imageButton25)

        var luces =""
        imageButton26.setOnClickListener(){
            luces=""
            imageView.setImageResource(R.drawable.battery)
            imageView2.setImageResource(R.drawable.battery)
            imageView3.setImageResource(R.drawable.battery)
            n=2
            cont=0
            lifes=3
            score=0
            textView7.setText("0")
            for (i:Int in 0..n-1){
                var sec = (0..24).random()
                if(luces.equals("")){
                    luces=sec.toString()
                }else{
                    val som = luces.split(",")
                    var sec = (0..24).random()
                    if(som.contains(sec.toString())){
                        while (som.contains(sec.toString())){
                            sec = (0..24).random()
                        }
                    }
                    luces=luces +"," + sec.toString()
                }
            }
            println("n= "+n+"lista: "+luces)
            for (i:Int in 0..24) {
                buttons[i].setTag("marcado")
                buttons[i].isClickable = true
                buttons[i].setImageResource(R.drawable.ideaoff)
            }
            GlobalScope.launch(context = Dispatchers.Main) {
                delay(2000)
                println(luces.count())
                val s =luces.split(",")
                for (i: Int in 0..s.count() - 1) {
                    buttons[Integer.parseInt(s[i])].setTag("CardBack")
                    buttons[Integer.parseInt(s[i])].setImageResource(R.drawable.ideaoff)
                }

            }
            val x= luces.split(",")
            for (i: Int in 0..x.count() - 1) {
                buttons[Integer.parseInt(x[i])].setImageResource(R.drawable.ideaon)
            }

        }

        for (i:Int in 0..24) {
            buttons[i].setOnClickListener {
                if (buttons[i].getTag().toString() == "CardBack"  ) {
                    buttons[i].setTag("marcado")
                    buttons[i].setImageResource(R.drawable.ideaon)
                    score=score+50
                    cont+=1
                }else{
                    lifes--
                    score=score-30
                    if (lifes==2){
                        imageView.setImageResource(R.drawable.transparente)
                    }else{
                        if (lifes==1){
                            imageView2.setImageResource(R.drawable.transparente)
                        }else{
                            if (lifes==0){
                                imageView3.setImageResource(R.drawable.transparente)
                                for (i:Int in 0..24) {
                                    buttons[i].isClickable = false
                                }
                                MaterialAlertDialogBuilder(requireContext())
                                .setTitle("Juego de Memoria")
                                .setMessage("Se le han agotado sus vidas")
                                .setNegativeButton("Terminar") { dialog, which ->
                                    // Respond to neutral button press
                                    val som = score.toString()
                                    val bundle = bundleOf("Score" to som)
                                    navController!!.navigate(R.id.action_juegoMemoriaFragment_to_score,bundle)
                                }
                                .setPositiveButton("Seguir") { dialog, which ->
                                    // Respond to positive button press
                                }
                                .show()
                            }
                        }
                    }
                }

                println("n: "+n+"y cont:"+cont)
                if(cont == n){
                    n+=1
                    println("n: "+n)
                    cont =0

                    for (i: Int in 0..24) {
                        buttons[i].setImageResource(R.drawable.ideaoff)
                        buttons[i].setTag("marcado")
                    }
                    luces =""
                    for (i:Int in 0..n-1){
                        var sec = (0..24).random()
                        if(luces.equals("")){
                            luces=sec.toString()
                        }else{
                            val som = luces.split(",")
                            var sec = (0..24).random()
                            if(som.contains(sec.toString())){
                                while (som.contains(sec.toString())){
                                    sec = (0..24).random()
                                }
                            }
                            luces=luces +"," + sec.toString()
                        }
                    }
                    println("n= "+n+"lista: "+luces)
                    GlobalScope.launch(context = Dispatchers.Main) {
                        delay(2000)
                        println(luces.count())
                        val s =luces.split(",")
                        for (i: Int in 0..s.count() - 1) {
                            buttons[Integer.parseInt(s[i])].setTag("CardBack")
                            buttons[Integer.parseInt(s[i])].setImageResource(R.drawable.ideaoff)
                        }

                    }
                    val x= luces.split(",")
                    for (i: Int in 0..x.count()- 1) {
                        buttons[Integer.parseInt(x[i])].setImageResource(R.drawable.ideaon)
                    }

                }
                //mandar info al score
                textView7.setText(score.toString())
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JuegoMemoriaFragment().apply {

            }
    }
}