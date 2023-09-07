package com.example.tipcalculatorapp

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.icu.text.NumberFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tipcalculatorapp.ui.theme.TipCalculatorAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    var amountInput by remember { mutableStateOf("") }
                    var tipPercent by remember { mutableStateOf("") }

                    val amount = amountInput.toDoubleOrNull() ?: 0.0
                    val tipPer = tipPercent.toDoubleOrNull() ?: 0.0

                    val tip = calTip(amount,tipPer)

                    Column(
                        modifier = Modifier
                            .padding(40.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.calculate_tip),
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                                .align(alignment = Alignment.Start)
                        )
                        TextField(
                            value = amountInput,
                            onValueChange = {
                                amountInput = it
                            },
                            label = {
                                Text(stringResource(id = R.string.bill_amount))
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .padding(bottom = 32.dp)
                                .fillMaxWidth()
                        )
                        TextField(
                            value = tipPercent,
                            onValueChange = {
                                tipPercent = it
                            },
                            label = {
                                Text(stringResource(id = R.string.how_was_the_service))
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .padding(bottom = 32.dp)
                                .fillMaxWidth()
                        )
                        Text(
                            text = stringResource(R.string.tip_amount, tip),
                            style = MaterialTheme.typography.displaySmall
                        )
                        Spacer(modifier = Modifier.height(150.dp))
                    }
                }
            }
        }
    }
}

private fun calTip(amount: Double, tipPercent: Double): String {
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}




