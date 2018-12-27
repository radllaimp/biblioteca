package biblioteca

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DoacaoServiceSpec extends Specification {

    DoacaoService doacaoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Doacao(...).save(flush: true, failOnError: true)
        //new Doacao(...).save(flush: true, failOnError: true)
        //Doacao doacao = new Doacao(...).save(flush: true, failOnError: true)
        //new Doacao(...).save(flush: true, failOnError: true)
        //new Doacao(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //doacao.id
    }

    void "test get"() {
        setupData()

        expect:
        doacaoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Doacao> doacaoList = doacaoService.list(max: 2, offset: 2)

        then:
        doacaoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        doacaoService.count() == 5
    }

    void "test delete"() {
        Long doacaoId = setupData()

        expect:
        doacaoService.count() == 5

        when:
        doacaoService.delete(doacaoId)
        sessionFactory.currentSession.flush()

        then:
        doacaoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Doacao doacao = new Doacao()
        doacaoService.save(doacao)

        then:
        doacao.id != null
    }
}
