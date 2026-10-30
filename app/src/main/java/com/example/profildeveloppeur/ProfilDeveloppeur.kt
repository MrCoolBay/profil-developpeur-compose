package com.example.profildeveloppeur

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

/**
 * Écran principal : il place la fiche dans l'écran.
 * Le padding de 16.dp appliqué ici est un padding EXTERNE : c'est le parent
 * qui décide de la marge autour de la fiche.
 */
@Composable
fun EcranProfil(
    nom: String,
    role: String,
    description: String,
    competences: List<String>,
    contact: String,
    portfolio: String,
    disponible: Boolean,
    onContacterClick: () -> Unit,
    onDisponibiliteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        FicheProfil(
            nom = nom,
            role = role,
            description = description,
            competences = competences,
            contact = contact,
            portfolio = portfolio,
            disponible = disponible,
            onContacterClick = onContacterClick,
            onDisponibiliteClick = onDisponibiliteClick,
            modifier = Modifier.padding(16.dp)
        )
    }
}

/**
 * Fiche de profil : une Surface qui regroupe toutes les informations.
 * Le padding de 20.dp appliqué au Column intérieur est un padding INTERNE :
 * il espace le contenu par rapport aux bords de la fiche.
 */
@Composable
fun FicheProfil(
    nom: String,
    role: String,
    description: String,
    competences: List<String>,
    contact: String,
    portfolio: String,
    disponible: Boolean,
    onContacterClick: () -> Unit,
    onDisponibiliteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            EnTeteProfil(nom = nom, role = role)

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 12.dp)
            )

            TitreSection(
                titre = "Compétences",
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
            )
            competences.forEach { competence ->
                Competence(
                    nom = competence,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            TitreSection(
                titre = "Contact",
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
            )
            InformationSecondaire(libelle = "E-mail", valeur = contact)
            InformationSecondaire(libelle = "Portfolio", valeur = portfolio)

            ZoneDisponibilite(
                disponible = disponible,
                onClick = onDisponibiliteClick,
                modifier = Modifier.padding(top = 16.dp)
            )

            BoutonAction(
                texte = "Contacter",
                onClick = onContacterClick,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

/** En-tête : nom et rôle du profil. */
@Composable
fun EnTeteProfil(
    nom: String,
    role: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = nom,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

/** Titre d'une section de la fiche (« Compétences », « Contact »…). */
@Composable
fun TitreSection(
    titre: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = titre,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

/** Une compétence affichée sous forme d'étiquette. */
@Composable
fun Competence(
    nom: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = nom,
            style = MaterialTheme.typography.labelLarge,
            // Padding interne : espace entre le texte et le bord de l'étiquette
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

/** Information secondaire sous la forme « libellé : valeur ». */
@Composable
fun InformationSecondaire(
    libelle: String,
    valeur: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$libelle : $valeur",
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

/**
 * Zone cliquable personnalisée indiquant la disponibilité.
 * Comme ce n'est pas un Button, on précise son intention pour l'accessibilité :
 * rôle de bouton, libellé de l'action et description lue par le lecteur d'écran.
 */
@Composable
fun ZoneDisponibilite(
    disponible: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val texte = if (disponible) "Disponible pour des missions" else "Non disponible actuellement"
    val detail = if (disponible) "Voir mes disponibilités" else "Voir quand je serai disponible"

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClickLabel = detail,
                role = Role.Button,
                onClick = onClick
            )
            .semantics { contentDescription = "$texte. $detail" },
        color = if (disponible) {
            MaterialTheme.colorScheme.tertiaryContainer
        } else {
            MaterialTheme.colorScheme.errorContainer
        },
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = texte,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = detail,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

/** Bouton d'action : il signale le clic au parent via le callback onClick. */
@Composable
fun BoutonAction(
    texte: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = texte)
    }
}

@Preview(showBackground = true, name = "Profil full-stack - disponible")
@Composable
fun ProfilFullStackPreview() {
    ProfilDeveloppeurTheme(dynamicColor = false) {
        EcranProfil(
            nom = "Fabien Lubin",
            role = "Développeur full-stack en alternance",
            description = "En alternance chez France Cyber Défense, je conçois des applications " +
                "web et mobiles performantes et sécurisées, de la maquette à la mise en production.",
            competences = listOf("Vue.js / Nuxt", "FastAPI", "React Native", "TypeScript", "Docker"),
            contact = "mrcoolbayshare@gmail.com",
            portfolio = "fabienlubin.fr",
            disponible = true,
            onContacterClick = {},
            onDisponibiliteClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Profil Android junior - non disponible")
@Composable
fun ProfilAndroidJuniorPreview() {
    ProfilDeveloppeurTheme(dynamicColor = false) {
        EcranProfil(
            nom = "Camille Martin",
            role = "Développeuse Android junior",
            description = "Passionnée par les interfaces mobiles, je construis des " +
                "applications claires et accessibles avec Kotlin et Jetpack Compose.",
            competences = listOf("Kotlin", "Jetpack Compose", "Git"),
            contact = "camille.martin@example.com",
            portfolio = "camille-martin.example.com",
            disponible = false,
            onContacterClick = {},
            onDisponibiliteClick = {}
        )
    }
}
