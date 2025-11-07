enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL } // Define os níveis de dificuldade possíveis

class Usuario(val nome: String, val idade: Int) // Representa um usuário da plataforma

data class ConteudoEducacional(var nome: String, val duracao: Int = 60) // Representa um conteúdo educacional dentro de uma formação

data class Formacao
(   
    val nome: String,
    val nivel: Nivel,
    var conteudos: List<ConteudoEducacional>
) {
    private val inscritos = mutableListOf<Usuario>()

    // Matricula um usuário, verificando duplicidade
    fun matricular(usuario: Usuario) {
        if (inscritos.contains(usuario)) {
            println("${usuario.nome} já está matriculado!")
        } else {
            inscritos.add(usuario)
            println("Seja bem-vindo ${usuario.nome}, você foi matriculado com sucesso!")
        }
    }

    // Calcula a carga horária total
    fun cargaHorariaTotal(): Int = conteudos.sumOf { it.duracao }

    // Lista os conteúdos disponíveis
    fun listarConteudos() {
        println("\n Esses São os conteúdos da formação $nome:")
        conteudos.forEachIndexed { index, conteudo ->
            println(" ${index + 1}. ${conteudo.nome} - ${conteudo.duracao} min")
        }
        println("Carga horária total: ${cargaHorariaTotal()} min")
    }

    // Lista os alunos inscritos
    fun listarInscritos() {
        println("\n Alunos matriculados na formação $nome:")
        if (inscritos.isEmpty()) {
            println(" Nenhum aluno matriculado ainda.")
        } else {
            inscritos.forEachIndexed { index, usuario ->
                println(" ${index + 1}. ${usuario.nome} (${usuario.idade} anos)")
            }
        }
    }
}

// Função principal — ponto de execução do programa
fun main() {
    val c1 = ConteudoEducacional("Introdução a Kotlin", 20)
    val c2 = ConteudoEducacional("POO em Kotlin", 30)
    val c3 = ConteudoEducacional("Desenvolvimento Mobile", 50)

    val formacaoKotlin = Formacao(
        nome = "Desenvolvimento Kotlin",
        nivel = Nivel.INTERMEDIARIO,
        conteudos = listOf(c1, c2, c3)
    )

    val usuario1 = Usuario("Gabriel Freitas", 20)
    val usuario2 = Usuario("Mariana Feitosa", 39)
    val usuario3 = Usuario("Alexandro Lima", 53)

    formacaoKotlin.matricular(usuario1)
    formacaoKotlin.matricular(usuario2)
    formacaoKotlin.matricular(usuario1) // Teste de duplicação

    formacaoKotlin.listarConteudos()
    formacaoKotlin.listarInscritos()
}
