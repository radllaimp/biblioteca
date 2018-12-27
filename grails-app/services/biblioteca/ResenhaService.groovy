package biblioteca

import grails.gorm.services.Service

@Service(Resenha)
interface ResenhaService {

    Resenha get(Serializable id)

    List<Resenha> list(Map args)

    Long count()

    void delete(Serializable id)

    Resenha save(Resenha resenha)

}