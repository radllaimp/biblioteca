package biblioteca

import grails.gorm.services.Service

@Service(Comentario)
interface ComentarioService {

    Comentario get(Serializable id)

    List<Comentario> list(Map args)

    Long count()

    void delete(Serializable id)

    Comentario save(Comentario comentario)

}