package biblioteca

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ResenhaController {

    ResenhaService resenhaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond resenhaService.list(params), model:[resenhaCount: resenhaService.count()]
    }

    def show(Long id) {
        respond resenhaService.get(id)
    }

    def create() {
        respond new Resenha(params)
    }

    def save(Resenha resenha) {
        if (resenha == null) {
            notFound()
            return
        }

        try {
            resenhaService.save(resenha)
        } catch (ValidationException e) {
            respond resenha.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'resenha.label', default: 'Resenha'), resenha.id])
                redirect resenha
            }
            '*' { respond resenha, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond resenhaService.get(id)
    }

    def update(Resenha resenha) {
        if (resenha == null) {
            notFound()
            return
        }

        try {
            resenhaService.save(resenha)
        } catch (ValidationException e) {
            respond resenha.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'resenha.label', default: 'Resenha'), resenha.id])
                redirect resenha
            }
            '*'{ respond resenha, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        resenhaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'resenha.label', default: 'Resenha'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'resenha.label', default: 'Resenha'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
