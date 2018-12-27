package biblioteca

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DoacaoController {

    DoacaoService doacaoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond doacaoService.list(params), model:[doacaoCount: doacaoService.count()]
    }

    def show(Long id) {
        respond doacaoService.get(id)
    }

    def create() {
        respond new Doacao(params)
    }

    def save(Doacao doacao) {
        if (doacao == null) {
            notFound()
            return
        }

        try {
            doacaoService.save(doacao)
        } catch (ValidationException e) {
            respond doacao.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'doacao.label', default: 'Doacao'), doacao.id])
                redirect doacao
            }
            '*' { respond doacao, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond doacaoService.get(id)
    }

    def update(Doacao doacao) {
        if (doacao == null) {
            notFound()
            return
        }

        try {
            doacaoService.save(doacao)
        } catch (ValidationException e) {
            respond doacao.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'doacao.label', default: 'Doacao'), doacao.id])
                redirect doacao
            }
            '*'{ respond doacao, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        doacaoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'doacao.label', default: 'Doacao'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'doacao.label', default: 'Doacao'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
