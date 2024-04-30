package uz.itschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.itschool.ui.theme.BankAppTheme

class EnterCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    EnterCard()

                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun EnterCard() {
    var cardNum by remember { mutableStateOf("") }
    var sum by remember { mutableStateOf("") }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp, 50.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Add Account Number", fontFamily = FontFamily(Font(R.font.poppins_medium)), fontSize = 25.sp, modifier = Modifier.padding(bottom=80.dp))
            TextField(
                value = cardNum,
                onValueChange = {
                    cardNum = it
                },
                label = { Text("Enter card number", fontFamily = FontFamily(Font(R.font.poppins_medium))) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFCD110),
                    unfocusedBorderColor = Color(0xFFFCD110),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, start = 10.dp, end = 10.dp)
                    .clip(MaterialTheme.shapes.medium),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            Row(){
                TextField(
                    value = sum,
                    onValueChange = {
                        sum = it
                    },
                    modifier = Modifier
                        .width(100.dp)
                        .padding(end = 15.dp),
                    label = { Text("0", fontFamily = FontFamily(Font(R.font.poppins_medium)))}
                )
                Text(text = "UZS", fontFamily = FontFamily(Font(R.font.poppins_medium)), fontSize = 20.sp, modifier = Modifier.padding(top=27.dp))
            }
            
            Text(modifier = Modifier.fillMaxWidth().padding(start = 25.dp, top = 20.dp), text = "Odiljonov Odil", fontFamily = FontFamily(Font(R.font.poppins_medium)), fontSize = 17.sp)

            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .padding(top = 370.dp)
                .width(250.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFCD110))) {
                Text(text = "Pay", fontFamily = FontFamily(Font(R.font.poppins_medium)), fontSize = 20.sp)
            }

        }
    }
}