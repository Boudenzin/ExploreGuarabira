package com.example.exploreguarabiraapp.data

import androidx.compose.ui.graphics.Color

val mockCategorias = listOf(
    Categoria(id = "cafes", nome = "Cafeterias", iconResId = android.R.drawable.ic_menu_search, cor = Color(0xFF6D4C41)),
    Categoria(id = "restaurantes", nome = "Restaurantes", iconResId = android.R.drawable.ic_menu_agenda, cor = Color(0xFFD32F2F)),
    Categoria(id = "tapiocarias", nome = "Tapiocarias", iconResId = android.R.drawable.ic_menu_edit, cor = Color(0xFFE57373)),
    Categoria(id = "centros-esportivos", nome = "Fitness & Esportes", iconResId = android.R.drawable.ic_menu_today, cor = Color(0xFF1E88E5)),
    Categoria(id = "shopping-centers", nome = "Lojas & Shopping", iconResId = android.R.drawable.ic_menu_recent_history, cor = Color(0xFF43A047)),
)

fun getCategoriaById(id: String): Categoria {
    return mockCategorias.first {it.id == id}
}

val mockAvaliacoesCafe = listOf(
    Avaliacao("Pedro Peterson", 5, "Melhor café da cidade! Ambiente ideal para leitura."),
    Avaliacao("Ana Beatriz Rocha", 4, "Sempre volto pelo pão de queijo. Perfeito."),
    Avaliacao("Larissa Pereira", 5, "Grãos premium e atendimento impecável."),
)

val mockAvaliacoesGourmet = listOf(
    Avaliacao("Ana D.", 5, "Experiência gastronômica incrível. O risoto é divino."),
    Avaliacao("Bruno E.", 4, "Ótimo para um jantar romântico, mas o preço é salgado."),
)

val mockAvaliacoesTapioca = listOf(
    Avaliacao("Felipe G.", 5, "A tapioca de carne de sol é a melhor que já comi!"),
    Avaliacao("Gabi H.", 4, "Rápido, gostoso e barato. Ponto de parada obrigatório."),
)

val mockAvaliacoesAcademia = listOf(
    Avaliacao("Higor I.", 5, "Equipamentos novos e professores atenciosos."),
    Avaliacao("Júlia L.", 4, "Aulas de funcional excelentes, recomendo a todos!"),
)



// ----------------------------------------------------------------------
// DADOS MOCK - LOCAIS (Usando a data class Local diretamente)
// ----------------------------------------------------------------------

val mockLocais: List<Local> = listOf(
    // --- CATEGORIA: CAFETERIAS ---
    Local(
        id = "cafe-1",
        nome = "Café Aroma",
        descricao = "Cafeteria aconchegante com grãos especiais e ambiente familiar. Perfeito para trabalhar ou encontrar amigos.",
        categoria = getCategoriaById("cafes"),
        avaliacaoMedia = 4.8,
        totalAvaliacoes = 156,
        preco = "$$",
        endereco = "Rua das Flores, 123 - Centro",
        horario = "Seg-Sex: 7h-19h",
        telefone = "(83) 3456-7890", // Adaptado para DDD de Guarabira/PB
        conhecidoPor = listOf("Café expresso premiado", "Bolos caseiros", "Wi-Fi gratuito", "Ambiente tranquilo"),
        imageUrl = "https://images.unsplash.com/photo-1501339847302-ac426a4a7cbb?w=400",
        avaliacoes = mockAvaliacoesCafe
    ),
    Local(
        id = "cafe-2",
        nome = "Café do Ponto",
        descricao = "Tradicional café da manhã e lanches rápidos. Atende a comunidade há mais de 15 anos.",
        categoria = getCategoriaById("cafes"),
        avaliacaoMedia = 4.6,
        totalAvaliacoes = 89,
        preco = "$",
        endereco = "Av. Principal, 456 - Bairro Novo",
        horario = "Seg-Sáb: 6h30-20h",
        telefone = "(83) 3456-7891",
        conhecidoPor = listOf("Café da manhã completo", "Pão na chapa", "Atendimento rápido", "Preços acessíveis"),
        imageUrl = "https://images.unsplash.com/photo-1554118811-1e0d58224f24?w=400",
        avaliacoes = listOf(Avaliacao("Pedro F.", 4, "Café com leite tradicional e saboroso."))
    ),

    // --- CATEGORIA: RESTAURANTES ---
    Local(
        id = "rest-1",
        nome = "Restaurante Sabor da Terra",
        descricao = "Comida caseira com tempero especial da vovó. Buffet variado e pratos à la carte.",
        categoria = getCategoriaById("restaurantes"),
        avaliacaoMedia = 4.7,
        totalAvaliacoes = 312,
        preco = "$$",
        endereco = "Av. Central, 234 - Centro",
        horario = "Seg-Dom: 11h-23h",
        telefone = "(83) 3456-7894",
        conhecidoPor = listOf("Feijoada aos sábados", "Buffet self-service", "Sobremesas caseiras", "Ambiente familiar"),
        imageUrl = "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=400",
        avaliacoes = mockAvaliacoesGourmet
    ),
    Local(
        id = "rest-2",
        nome = "Churrascaria Bom Gosto",
        descricao = "Rodízio de carnes nobres com buffet completo. Ambiente climatizado e espaçoso.",
        categoria = getCategoriaById("restaurantes"),
        avaliacaoMedia = 4.8,
        totalAvaliacoes = 445,
        preco = "$$$",
        endereco = "Rua da Liberdade, 567 - Vila Nova",
        horario = "Seg-Dom: 11h-15h, 18h-23h",
        telefone = "(83) 3456-7895",
        conhecidoPor = listOf("Picanha de primeira", "Rodízio completo", "Buffet variado", "Churrasco no almoço"),
        imageUrl = "https://images.unsplash.com/photo-1555939594-58d7cb561ad1?w=400",
        avaliacoes = listOf(Avaliacao("Rafa S.", 5, "Carnes sempre no ponto. Top demais."))
    ),

    // --- CATEGORIA: TAPIOCARIAS ---
    Local(
        id = "tap-1",
        nome = "Tapioca & Sabor",
        descricao = "Tapiocas recheadas e cuscuz nordestino. Café da manhã típico regional.",
        categoria = getCategoriaById("tapiocarias"),
        avaliacaoMedia = 4.5,
        totalAvaliacoes = 143,
        preco = "$",
        endereco = "Rua Nordeste, 123 - Centro",
        horario = "Seg-Sáb: 6h-14h",
        telefone = "(83) 3456-7899",
        conhecidoPor = listOf("Tapioca de carne seca", "Cuscuz com leite", "Suco natural", "Café regional"),
        imageUrl = "https://images.unsplash.com/photo-1604147706283-d7119b5b822c?w=400",
        avaliacoes = mockAvaliacoesTapioca
    ),

    // --- CATEGORIA: CENTROS ESPORTIVOS ---
    Local(
        id = "sport-1",
        nome = "Academia FitLife",
        descricao = "Academia completa com equipamentos modernos e profissionais qualificados.",
        categoria = getCategoriaById("centros-esportivos"),
        avaliacaoMedia = 4.6,
        totalAvaliacoes = 234,
        preco = "$$",
        endereco = "Av. dos Esportes, 456 - Jardim Atlético",
        horario = "Seg-Sex: 6h-22h, Sáb: 8h-14h",
        telefone = "(83) 3456-7803",
        conhecidoPor = listOf("Musculação completa", "Aulas coletivas", "Personal trainer", "Área cardio"),
        imageUrl = "https://images.unsplash.com/photo-1534438327276-14e5300c3a48?w=400",
        avaliacoes = mockAvaliacoesAcademia
    ),

    // --- CATEGORIA: SHOPPING CENTERS ---
    Local(
        id = "shop-1",
        nome = "Shopping Central Plaza",
        descricao = "Shopping tradicional do centro com lojas variadas e praça de alimentação completa.",
        categoria = getCategoriaById("shopping-centers"),
        avaliacaoMedia = 4.5,
        totalAvaliacoes = 1234,
        preco = "$$",
        endereco = "Av. Brasil, 1000 - Centro",
        horario = "Seg-Sáb: 10h-22h, Dom: 12h-21h",
        telefone = "(83) 3456-7807",
        conhecidoPor = listOf("Cinema multiplex", "Praça de alimentação", "Lojas de departamento", "Estacionamento coberto"),
        imageUrl = "https://images.unsplash.com/photo-1555529669-e69e7aa0ba9a?w=400",
        avaliacoes = listOf(Avaliacao("Vitor W.", 4, "Ótimo lugar para passear no final de semana."))
    ),
)
