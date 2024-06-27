package com.example.calculatorapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    var context=LocalContext.current
    var num by rememberSaveable {
        mutableStateOf("")
    }
    var numBtn by rememberSaveable {
        mutableStateOf(1)
    }
    var numSavedLong by rememberSaveable {
        mutableStateOf<Long>(0)
    }
    var operation by rememberSaveable {
        mutableStateOf("")
    }
    var numSavedDouble by rememberSaveable {
        mutableStateOf<Double>(0.0)
    }
    var listOfSymbolsAtTheTop= arrayListOf("%","±","C")
    Column(modifier = Modifier
        .background(Color.Black)
        .fillMaxSize()) {
    var listOfSymbolsAtTheRight= arrayListOf("÷","x","-","+","=")

        Text(
            text = num, fontSize = 104.sp, color = Color.White, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)
        )
        Row(modifier = Modifier.offset(0.dp,-30.dp)) {


            Box(modifier = Modifier.weight(0.7f)) {
                Column {


                    Row(modifier = Modifier.weight(1f)) {
                        var i=0
                        for (i in 0..<listOfSymbolsAtTheTop.size) {
                            Button(
                                onClick = {
                                if (num.isEmpty() && operation==""){
                                    Toast.makeText(context, "Введи число, друг", Toast.LENGTH_SHORT).show()
                                }
                                    else {
                                    if (listOfSymbolsAtTheTop[i] == "±") {
                                        if (operation != "") {
                                            if (num.count { c -> c == '.' } == 1) {
                                                num = (numSavedDouble * (-1)).toString()
                                                numSavedDouble = num.toDouble()
                                                operation = ""
                                            } else {
                                                num = (numSavedLong * (-1)).toString()
                                                numSavedLong = num.toLong()
                                                operation = ""
                                            }
                                        } else {
                                            if (num.count { c -> c == '.' } == 1) {
                                                num = (num.toDouble() * (-1)).toString()
                                                numSavedDouble = num.toDouble()
                                                operation = ""
                                            } else {
                                                num = (num.toLong() * (-1)).toString()
                                                numSavedLong = num.toLong()
                                                operation = ""
                                            }
                                        }
                                    } else if (listOfSymbolsAtTheTop[i] == "C") {
                                        num = ""
                                        numSavedLong = 0
                                        numSavedDouble = 0.0
                                        operation = ""
                                    } else if (operation == "") {
                                        operation = listOfSymbolsAtTheTop[i]
                                        if (num.count { c -> c == '.' } == 1) {
                                            numSavedDouble = num.toDouble()
                                        } else {
                                            numSavedLong = num.toLong()
                                        }
                                        num = ""
                                    }

                                        else if (operation=="") {
                                            operation = listOfSymbolsAtTheTop[i]
                                            if (num.count{c->c== '.' }==1) {
                                                numSavedDouble = num.toDouble()
                                            }else{
                                                numSavedLong = num.toLong()
                                            }
                                            num=""
                                        }

                                     else {
                                        if (operation == "%") {
                                            if (num.count { c -> c == '.' } == 1 || numSavedDouble != 0.0) {
                                                num = (numSavedDouble % num.toDouble()).toString()
                                                operation = listOfSymbolsAtTheTop[i]
                                                numSavedDouble = num.toDouble()
                                            } else {
                                                num = (numSavedLong % num.toLong()).toString()
                                                operation = listOfSymbolsAtTheTop[i]
                                                numSavedLong = num.toLong()
                                            }
                                        }
                                        else if (operation == "÷") {
                                            if (num.count { c -> c == '.' } == 1 || numSavedDouble != 0.0) {
                                                num = (numSavedDouble / 100 * num.toDouble()).toString()
                                                operation = listOfSymbolsAtTheTop[i]
                                                numSavedDouble = num.toDouble()
                                            } else {
                                                num = (numSavedLong / 100 * num.toLong()).toString()
                                                operation = listOfSymbolsAtTheTop[i]
                                                numSavedLong = num.toLong()
                                            }
                                        }
                                        else if (operation == "x") {
                                            if (num.count { c -> c == '.' } == 1 || numSavedDouble != 0.0) {
                                                num = (numSavedDouble * num.toDouble()).toString()
                                                operation = listOfSymbolsAtTheTop[i]
                                                numSavedDouble = num.toDouble()
                                            } else {
                                                num = (numSavedLong * num.toLong()).toString()
                                                operation = listOfSymbolsAtTheTop[i]
                                                numSavedLong = num.toLong()
                                            }
                                        }
                                        else if (operation == "-") {
                                            if (num.count { c -> c == '.' } == 1 || numSavedDouble != 0.0) {
                                                num = (numSavedDouble - num.toDouble()).toString()
                                                operation = listOfSymbolsAtTheTop[i]
                                                numSavedDouble = num.toDouble()
                                            } else {
                                                num = (numSavedLong - num.toLong()).toString()
                                                operation = listOfSymbolsAtTheTop[i]
                                                numSavedLong = num.toLong()
                                            }
                                        }
                                        else if (operation == "+") {
                                            if (num.count { c -> c == '.' } == 1 || numSavedDouble != 0.0) {
                                                num = (numSavedDouble + num.toDouble()).toString()
                                                operation = listOfSymbolsAtTheTop[i]
                                                numSavedDouble = num.toDouble()
                                            } else {

                                                num = (numSavedLong + num.toLong()).toString()
                                                operation = listOfSymbolsAtTheTop[i]
                                                numSavedLong = num.toLong()
                                            }
                                        }
                                    }

                                    }


                                },
                                colors = ButtonDefaults.buttonColors(Color.Gray),
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) {
                                Text(text = listOfSymbolsAtTheTop[i], fontSize = 25.sp)
                            }

                        }
                    }
                    var numB=1
                    repeat(3) {
                        Row(modifier = Modifier.weight(1f)) {

                            repeat(3) {
                            var numButtonThis=numB
                                Button(
                                    onClick = {
                                        if (operation!=""&& (num==numSavedLong.toString()||num==numSavedDouble.toString())){
                                            num=""
                                        }
                                        num += numButtonThis.toString()
                                              if (num.toMutableList()[0]=='0' && num.toMutableList()[1]!='.'){
                                                  num= num.toMutableList().removeAt(0).toString()
                                              }
                                    },
                                    modifier = Modifier
                                        .weight(0.3f)
                                        .fillMaxHeight()
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
                            onClick = { if(num.isEmpty() || num.length>=1 ) num += "0" },
                            modifier = Modifier
                                .padding(3.dp)
                                .fillMaxHeight()
                                .weight(0.6f),
                            colors = ButtonDefaults.buttonColors(Color.DarkGray)
                        ) {
                            Text(text = "0", fontSize = 25.sp)
                        }
                        Button(
                            onClick = { num+="." }, modifier = Modifier
                                .padding(3.dp)
                                .fillMaxHeight()
                                .weight(0.3f), colors = ButtonDefaults.buttonColors(Color.DarkGray)
                        ) {
                            Text(text = ".", fontSize = 25.sp)


                        }
                    }
                }
            }//                arrayListOf("÷","x","-","+","=")
            //if (listOfSymbolsAtTheRight[j]=="÷") numSavedLong/100*num.toLong()
            Column(modifier=Modifier.weight(0.24f)) {
                for (j in 0..<listOfSymbolsAtTheRight.size){
                    Button(onClick = {
                        if (num.isEmpty() && operation == "") {
                            Toast.makeText(context, "Введи число, друг", Toast.LENGTH_SHORT).show()
                        } else {


                            if (listOfSymbolsAtTheRight[j] == "=") {
                                if (operation == "%") {
                                    if (num.count { c -> c == '.' } == 1 || numSavedDouble != 0.0) {
                                        num = (numSavedDouble % num.toDouble()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedDouble = num.toDouble()
                                    } else {
                                        num = (numSavedLong % num.toLong()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedLong = num.toLong()
                                    }
                                }
                                if (operation == "÷") {
                                    if (num.count { c -> c == '.' } == 1 || numSavedDouble != 0.0) {
                                        num = (numSavedDouble / num.toDouble()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedDouble = num.toDouble()
                                    } else {
                                        num = (numSavedLong / num.toLong()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedLong = num.toLong()
                                    }
                                }
                                if (operation == "x") {
                                    if (num.count { c -> c == '.' } == 1 || numSavedDouble != 0.0) {
                                        num = (numSavedDouble * num.toDouble()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedDouble = num.toDouble()
                                    } else {
                                        num = (numSavedLong * num.toLong()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedLong = num.toLong()
                                    }
                                }
                                if (operation == "-") {
                                    if (num.count { c -> c == '.' } == 1 || numSavedDouble != 0.0) {
                                        num = (numSavedDouble - num.toDouble()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedDouble = num.toDouble()
                                    } else {
                                        num = (numSavedLong - num.toLong()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedLong = num.toLong()
                                    }
                                }
                                if (operation == "+") {
                                    if (num.count { c -> c == '.' } == 1 || numSavedDouble != 0.0) {
                                        num = (numSavedDouble + num.toDouble()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedDouble = num.toDouble()
                                    } else {
                                        num = (numSavedLong + num.toLong()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedLong = num.toLong()
                                    }
                                }
                            }



                        else if (operation == "") {
                                operation = listOfSymbolsAtTheRight[j]
                                if (num.count { c -> c == '.' } == 1) {
                                    numSavedDouble = num.toDouble()
                                } else {
                                    numSavedLong = num.toLong()
                                }
                                num = ""
                            } else {
                                if (operation == "%") {
                                    if (num.count { c -> c == '.' } == 1 || numSavedDouble != 0.0) {
                                        num = (numSavedDouble % num.toDouble()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedDouble = num.toDouble()
                                    } else {
                                        num = (numSavedLong % num.toLong()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedLong = num.toLong()
                                    }
                                }
                                else if (operation == "÷") {
                                    if (num.count { c -> c == '.' } == 1 || numSavedDouble != 0.0) {
                                        num = (numSavedDouble /  num.toDouble()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedDouble = num.toDouble()
                                    } else {
                                        num = (numSavedLong / num.toLong()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedLong = num.toLong()
                                    }
                                }
                                else if (operation == "x") {
                                    if (num.count { c -> c == '.' } == 1 || numSavedDouble != 0.0) {
                                        num = (numSavedDouble * num.toDouble()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedDouble = num.toDouble()
                                    } else {
                                        num = (numSavedLong * num.toLong()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedLong = num.toLong()
                                    }
                                }
                                else if (operation == "-") {
                                    if (num.count { c -> c == '.' } == 1 || numSavedDouble != 0.0) {
                                        num = (numSavedDouble - num.toDouble()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedDouble = num.toDouble()
                                    } else {
                                        num = (numSavedLong - num.toLong()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedLong = num.toLong()
                                    }
                                }
                                else if (operation == "+") {
                                    if (num.count { c -> c == '.' } == 1 || numSavedDouble != 0.0) {
                                        num = (numSavedDouble + num.toDouble()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedDouble = num.toDouble()
                                    } else {

                                        num = (numSavedLong + num.toLong()).toString()
                                        operation = listOfSymbolsAtTheRight[j]
                                        numSavedLong = num.toLong()
                                    }
                                }
                            }
                        }
                        }


                        , colors = ButtonDefaults.buttonColors(Color.Green), modifier = Modifier
                        .weight(0.25f)
                        .fillMaxWidth(1f)
                        .padding(3.dp)) {
                     Text(text =listOfSymbolsAtTheRight[j],fontSize = 25.sp )
                    }
                }
            }
        }
    }

}

