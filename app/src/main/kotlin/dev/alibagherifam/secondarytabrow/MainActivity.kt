package dev.alibagherifam.secondarytabrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Scaffold { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        TabRowsComparison()
                    }
                }
            }
        }
    }
}

@Composable
fun TabRowsComparison() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(horizontalAlignment = Alignment.End) {
            Text("Primary Tab Row")
            FruitPrimaryTabRow()

            Spacer(Modifier.height(32.dp))
            Text("Secondary Tab Row")
            FruitSecondaryTabRow()
        }
    }
}

@Composable
fun FruitPrimaryTabRow() {
    val state = remember { FruitSate() }
    TabRow(state.selectedIndex) {
        for (index in state.fruits.indices) {
            Tab(
                selected = (index == state.selectedIndex),
                onClick = { state.selectedIndex = index },
                text = {
                    Text(text = state.fruits[index])
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitSecondaryTabRow() {
    val state = remember { FruitSate() }
    SecondaryTabRow(state.selectedIndex) {
        for (index in state.fruits.indices) {
            Tab(
                selected = (index == state.selectedIndex),
                onClick = { state.selectedIndex = index },
                text = {
                    Text(text = state.fruits[index])
                }
            )
        }
    }
}

class FruitSate {
    val fruits = listOf("Apple", "Orange", "Carrot", "Banana")

    var selectedIndex by mutableIntStateOf(0)
}

@Preview
@Composable
fun TabRowComparisonPreview() {
    MaterialTheme {
        TabRowsComparison()
    }
}
