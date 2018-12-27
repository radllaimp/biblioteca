package biblioteca

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ComentarioServiceSpec extends Specification {

    ComentarioService comentarioService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Comentario(...).save(flush: true, failOnError: true)
        //new Comentario(...).save(flush: true, failOnError: true)
        //Comentario comentario = new Comentario(...).save(flush: true, failOnError: true)
        //new Comentario(...).save(flush: true, failOnError: true)
        //new Comentario(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //comentario.id
    }

    void "test get"() {
        setupData()

        expect:
        comentarioService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Comentario> comentarioList = comentarioService.list(max: 2, offset: 2)

        then:
        comentarioList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        comentarioService.count() == 5
    }

    void "test delete"() {
        Long comentarioId = setupData()

        expect:
        comentarioService.count() == 5

        when:
        comentarioService.delete(comentarioId)
        sessionFactory.currentSession.flush()

        then:
        comentarioService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Comentario comentario = new Comentario()
        comentarioService.save(comentario)

        then:
        comentario.id != null
    }
}
