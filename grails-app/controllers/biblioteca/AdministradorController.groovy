package biblioteca

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AdministradorController {

    AdministradorService administradorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond administradorService.list(params), model:[administradorCount: administradorService.count()]
    }

    def show(Long id) {
        respond administradorService.get(id)
    }

    def create() {
        respond new Administrador(params)
    }

    def save(Administrador administrador) {
        if (administrador == null) {
            notFound()
            return
        }

        try {
            administradorService.save(administrador)
        } catch (ValidationException e) {
            respond administrador.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'administrador.label', default: 'Administrador'), administrador.id])
                redirect administrador
            }
            '*' { respond administrador, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond administradorService.get(id)
    }

    def update(Administrador administrador) {
        if (administrador == null) {
            notFound()
            return
        }

        try {
            administradorService.save(administrador)
        } catch (ValidationException e) {
            respond administrador.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'administrador.label', default: 'Administrador'), administrador.id])
                redirect administrador
            }
            '*'{ respond administrador, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        administradorService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'administrador.label', default: 'Administrador'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'administrador.label', default: 'Administrador'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
