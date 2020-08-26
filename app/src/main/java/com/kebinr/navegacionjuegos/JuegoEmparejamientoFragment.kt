package com.kebinr.navegacionjuegos

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_juego_emparejamiento.*


class JuegoEmparejamientoFragment : Fragment() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_juego_emparejamiento, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val images: MutableList<Int> = mutableListOf(R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6,R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6)
        val buttons: Array<ImageButton> = arrayOf(imageButton,imageButton2,imageButton3,imageButton4,imageButton5,imageButton6,imageButton7,imageButton8,imageButton9,imageButton10,imageButton11,imageButton12)
        var clicked =0
        var turnover =false
        var lastclicked = -1
        images.shuffle()
        for (i:Int in 0..11){
            buttons[i].setTag("CardBack")
            buttons[i].setOnClickListener {
                if (buttons[i].getTag().toString()=="CardBack" && !turnover){
                    buttons[i].setImageResource(images[i])
                    buttons[i].setTag(images[i])
                    if (clicked==0){
                        lastclicked=i
                    }
                    clicked++
                }
                //gano puntos
                if(clicked ==2){
                    turnover = true
                    if(buttons[i].getTag().toString()==buttons[lastclicked].getTag().toString()){
                        buttons[i].isClickable = false
                        buttons[lastclicked].isClickable = false
                        val int =Integer.parseInt(textView.text.toString()) +  100
                        textView.text = int.toString()
                        turnover=false
                        clicked=0
                        if(Integer.parseInt(textView.text.toString()) ==600){
                            textView3.text ="WIN"
                            MaterialAlertDialogBuilder(requireContext())
                                .setTitle("Juego de Emparejamiento")
                                .setMessage("Nivel Completado")
                                .setNegativeButton("Terminar") { dialog, which ->
                                    // Enviar puntaje a pantalla
                                    val som = int.toString()
                                    val bundle = bundleOf("Score" to som)
                                    navController!!.navigate(R.id.action_juegoEmparejamientoFragment_to_score,bundle)
                                }.setPositiveButton("Seguir"){dialog, which->
                                    //mantener en pantalla

                                }
                                .show()
                        }
                    }else{
                        //Temporizador
                        val handler = Handler()
                        handler.postDelayed({
                            buttons[i].setImageResource(R.drawable.pregunta)
                            buttons[lastclicked].setImageResource(R.drawable.pregunta)
                            buttons[i].setTag("CardBack")
                            buttons[lastclicked].setTag("CardBack")
                            clicked=0
                            turnover=false
                        }, 1000)
                    }
                }
            }
        }
        imageButton17.setOnClickListener {
            images.shuffle()
            clicked=0
            turnover=false
            textView.text = "0"
            for (i:Int in 0..11){
                buttons[i].setImageResource(R.drawable.pregunta)
                buttons[i].setTag("CardBack")
                buttons[i].isClickable=true
                textView3.text=""
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JuegoEmparejamientoFragment().apply {
            }
    }
}