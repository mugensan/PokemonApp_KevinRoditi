package com.kevinroditi.pokemonapp_kevinroditi.presentation.home.components

/**
 * I'm using animatedFloatAsState fro smooth press animation
 */
@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: () -> Unit

) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.9f else 1f,
        animationSpec = tween(durationMillis = 120),
        label = "card_scale_animation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable(
                onClick = onClick,
                onClickLabel = "Open Pokemon Detail"
            ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = "#${pokemon.id}",
                style = MaterialTheme.typography.labelMedium

            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = pokemon.name.replaceFirstChar {
                    it.upperCase()
                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}