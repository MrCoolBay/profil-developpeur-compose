package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDeveloppeurTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EcranProfil(
                        nom = "Fabien Lubin",
                        role = "Développeur full-stack en alternance",
                        description = "En alternance chez France Cyber Défense, je conçois des applications " +
                            "web et mobiles performantes et sécurisées, de la maquette à la mise en production.",
                        competences = listOf("Vue.js / Nuxt", "FastAPI", "React Native", "TypeScript", "Docker"),
                        contact = "mrcoolbayshare@gmail.com",
                        portfolio = "fabienlubin.fr",
                        disponible = true,
                        onContacterClick = {
                            // Plus tard : ouvrir l'application e-mail
                        },
                        onDisponibiliteClick = {
                            // Plus tard : afficher les disponibilités pour des missions
                        },
                        // innerPadding évite que le contenu passe sous les barres système
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
