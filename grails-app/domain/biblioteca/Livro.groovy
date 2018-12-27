package biblioteca

class Livro {
    String titulo
    String isbn
    String autor
    static belongsTo = [administrador: Administrador, usuario: Usuario]
    static hasMany = [resenhas: Resenha]
    static constraints = {
    isbn maxSize: 13
    }
}
