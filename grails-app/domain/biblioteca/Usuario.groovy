package biblioteca

class Usuario {
    String nome
    String senha
    static hasMany = [livros: Livro]
    static constraints = {
     nome maxSize: 60
     senha maxsize: 10
    }

}
