package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

/**
 * This activity allows the user to calculate tip of a service and
 * view the result on the screen
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    /**
     * Calculate the tip and update the result with the screen
     */
    private fun calculateTip(){
        //Find the Edittext in the layout which user will update the tip amount
        val stringInTextFiled = binding.costOfServiceEditText.text.toString()
        // Converting the received tip amount into Double or Null
        val cost = stringInTextFiled.toDoubleOrNull()
        if (cost== null || cost== 0.0){
           displayTip(0.0)
            return
        }
        //Find the Radiobutton in the layout which customer is checked
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        //Calculating tip amount without rounding up
        var tip = tipPercentage * cost

        //Find the switch in the layout when customer is checked
        if (binding.roundUpSwitch.isChecked){
            tip = kotlin.math.ceil(tip)
        }
        //Calling the function to update the result with screen
        //by rounding up the tip and converting it into currency format of the customer

       displayTip(tip)

    }private fun displayTip(tip : Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}
