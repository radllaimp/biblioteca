package biblioteca

import grails.gorm.services.Service

@Service(Doacao)
interface DoacaoService {

    Doacao get(Serializable id)

    List<Doacao> list(Map args)

    Long count()

    void delete(Serializable id)

    Doacao save(Doacao doacao)

}