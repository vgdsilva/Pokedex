package com.vgdsilva.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vgdsilva.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                var navController = rememberNavController();
                NavHost(navController = navController, startDestination = "PokemonListPage") {
                    composable("PokemonListPage") {

                    }
                    composable("PokemonDetail/{dominantColor}/{pokemonId}",
                               arguments = listOf(
                                   navArgument("dominantColor"){
                                        type = NavType.IntType
                                   },
                                   navArgument("pokemonId"){
                                        type = NavType.StringType
                                   }
                               )
                    ) {
                        var dominantColor = remember {
                            val color = it.arguments?.getInt("dominantColor")
                            color?.let { Color(it) } ?: Color.White
                        }
                        var pokemonId = remember {
                            it.arguments?.getString("pokemonId")
                        }
                    }
                }
            }
        }
    }
}