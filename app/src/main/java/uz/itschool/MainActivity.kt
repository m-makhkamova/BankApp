package uz.itschool

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView.ScaleType
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.itschool.ui.theme.BankAppTheme


data class Bank(val logo:Int, val name:String)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingPreview()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BankAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp, 40.dp),
            color = MaterialTheme.colorScheme.background
        ){

            var banks = mutableListOf<Bank>()
            banks.add(Bank(R.drawable.uzumbank, "Uzum bank"))
            banks.add(Bank(R.drawable.tbc, "TBC bank"))
            banks.add(Bank(R.drawable.qqb, "Qishloq Qurilish banki"))
            banks.add(Bank(R.drawable.orient, "Orient Finans banki"))


            Scaffold(topBar = {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(20.dp), ){
                    Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24), contentDescription = null)
                    Text(text = "Payment", fontSize = 32.sp, fontFamily = FontFamily(Font(R.font.poppins_bold)), color = Color(0xFF00214E), modifier = Modifier.padding(top = 8.dp))
                }
            }){
                Column(modifier = Modifier.padding(top = 90.dp)){
                    Text(text = "Connect with Bank Account", fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.poppins_medium)), color = Color(0xFF00214E))
                    Text(text = "Search or select recipents bank", fontSize = 13.sp, fontFamily = FontFamily(Font(R.font.poppins_light)), color = Color(0xFFA0A0A0))

                    var text by remember { mutableStateOf("") }
                    var active by remember { mutableStateOf(false) }

                    SearchBar(
                        query = text,
                        onQueryChange = {
                                        text = it
                        },
                        onSearch = {active = false},
                        active = active,
                        onActiveChange = {
                            active = it
                        },
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .height(50.dp),
                        trailingIcon = {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = null,
                                modifier = Modifier.clickable {
                                    if(text.isNotEmpty()){
                                        text = ""
                                        }
                                        else{active = false}
                                })
                        },
                        placeholder = { Text(text = "Search Bank", fontSize = 10.sp)},
                    ) {}

                    LazyColumn(modifier = Modifier.padding(top = 40.dp)){
                        itemsIndexed(banks){ index, bank->
                            ProductItem(bank = bank)
                        }
                    }

                    }
                }
            }

        }
    }

@Composable
fun ProductItem(bank:Bank){
    val context = LocalContext.current
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.dp,Color(0xFF26B15D)),
        modifier = Modifier
            .fillMaxWidth()
            .size(width = 240.dp, height = 80.dp)
            .background(Color.White)
            .padding(vertical = 10.dp)
            .clickable {
                var intent = Intent(context, EnterCardActivity::class.java)
                context.startActivity(intent)
            },

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Image(
                painter = painterResource(id = bank.logo),
                contentDescription = "card",
                modifier = Modifier
                    .size(60.dp)
                    .padding(5.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(7.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    text = bank.name,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    style = MaterialTheme.typography.headlineLarge.copy(fontSize = 17.sp),
                    color = Color(0xFF00214E)
                )
            Image(
                painter = painterResource(id = R.drawable.next),
                contentDescription = "card",
                modifier = Modifier
                    .size(50.dp) // Adjust the size of the image as needed
                    .padding(3.dp),// Add padding around the image
            )

        }
    }
}