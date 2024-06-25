package com.example.calculatorapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column



import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorapplication.ui.theme.CalculatorApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            screen()
        }
    }
}


@SuppressLint("SuspiciousIndentation")
@Preview
@Composable
fun screen(){
    var num by rememberSaveable {
        mutableStateOf("")
    }
    var numBtn by rememberSaveable {
        mutableStateOf(1)
    }
    var numSaved by rememberSaveable {
        mutableStateOf<Long>(0)
    }
    var operation by rememberSaveable {
        mutableStateOf("")
    }
    var listOfSymbolsAtTheTop= arrayListOf("%","±","C")
    Column(modifier = Modifier.background(Color.Black).fillMaxSize()) {
    var listOfSymbolsAtTheRight= arrayListOf("÷","x","-","+","=")

        Text(
            text = num, fontSize = 54.sp, color = Color.White, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)
        )
        Row {


            Box(modifier = Modifier.weight(0.7f)) {
                Column {


                    Row(modifier = Modifier.weight(1f)) {
                        var i=0
                        repeat(3) {
                            Button(
                                onClick = {
                                    if (listOfSymbolsAtTheTop[i-1] == "%") {
                                        numSaved = num.toLong()
                                        if (numSaved.toString() != "0") {
                                            num = (numSaved % num.toLong()).toString()
                                            operation = "%"
                                        } else {
                                            operation = "%"
                                            num = ""
                                        }

                                    } else if (listOfSymbolsAtTheTop[i-1] == "±") {
                                        if (numSaved.toString()=="0") {
                                            numSaved = num.toLong()
                                            num = ""
                                            operation = "±"
                                        }
                                    } else {num = ""
                                    operation=""
                                    numSaved=0}


                                },
                                colors = ButtonDefaults.buttonColors(Color.Gray),
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) {
                                Text(text = listOfSymbolsAtTheTop[i], fontSize = 25.sp)
                            }
                            i+=1
                        }
                    }
                    var numB=1
                    repeat(3) {
                        Row(modifier = Modifier.weight(1f)) {

                            repeat(3) {
                            var numButtonThis=numB
                                Button(
                                    onClick = { num += numButtonThis.toString() },
                                    modifier = Modifier
                                        .weight(0.3f).fillMaxHeight()
                                        .padding(3.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                                ) {
                                    Text(
                                        fontSize = 25.sp,
                                        text = numB.toString(),
                                        color = Color.White,
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                }
                                numB += 1
                            }


                        }
                    }
                    Row (modifier = Modifier.weight(1f)){


                        Button(
                            onClick = { num += "0" },
                            modifier = Modifier
                                .padding(3.dp).fillMaxHeight()
                                .weight(0.6f),
                            colors = ButtonDefaults.buttonColors(Color.DarkGray)
                        ) {
                            Text(text = "0")
                        }
                        Button(
                            onClick = { num+="." }, modifier = Modifier
                                .padding(3.dp).fillMaxHeight()
                                .weight(0.3f), colors = ButtonDefaults.buttonColors(Color.DarkGray)
                        ) {
                            Text(text = ".")


                        }
                    }
                }
            }//                arrayListOf("÷","x","-","+","=")
            Column(modifier=Modifier.weight(0.24f)) {
                for (j in 0..listOfSymbolsAtTheRight.size-1){
                    Button(onClick = {if (listOfSymbolsAtTheRight[j]=="÷") numSaved/100*num.toLong()}, colors = ButtonDefaults.buttonColors(Color.Green), modifier = Modifier
                        .weight(0.25f).fillMaxWidth(1f)
                        .padding(3.dp)) {
                     Text(text =listOfSymbolsAtTheRight[j],fontSize = 25.sp )
                    }
                }
            }
        }
    }

}

