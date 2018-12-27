package biblioteca

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ResenhaServiceSpec extends Specification {

    ResenhaService resenhaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Resenha(...).save(flush: true, failOnError: true)
        //new Resenha(...).save(flush: true, failOnError: true)
        //Resenha resenha = new Resenha(...).save(flush: true, failOnError: true)
        //new Resenha(...).save(flush: true, failOnError: true)
        //new Resenha(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //resenha.id
    }

    void "test get"() {
        setupData()

        expect:
        resenhaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Resenha> resenhaList = resenhaService.list(max: 2, offset: 2)

        then:
        resenhaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        resenhaService.count() == 5
    }

    void "test delete"() {
        Long resenhaId = setupData()

        expect:
        resenhaService.count() == 5

        when:
        resenhaService.delete(resenhaId)
        sessionFactory.currentSession.flush()

        then:
        resenhaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Resenha resenha = new Resenha()
        resenhaService.save(resenha)

        then:
        resenha.id != null
    }
}
