package biblioteca

class Administrador {
    String nome;
    String senha;
    static hasMany = [videos: Video]
    static constraints = {
     nome maxSize: 60
     senha maxsize: 10
    }
}
