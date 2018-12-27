package biblioteca

class Resenha {
    String texto
    static belongsTo = [livro: Livro]
    static hasMany = [comentarios: Comentario]
    static constraints = {
    }
}
