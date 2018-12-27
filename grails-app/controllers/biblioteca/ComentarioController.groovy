package biblioteca

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ComentarioController {

    ComentarioService comentarioService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond comentarioService.list(params), model:[comentarioCount: comentarioService.count()]
    }

    def show(Long id) {
        respond comentarioService.get(id)
    }

    def create() {
        respond new Comentario(params)
    }

    def save(Comentario comentario) {
        if (comentario == null) {
            notFound()
            return
        }

        try {
            comentarioService.save(comentario)
        } catch (ValidationException e) {
            respond comentario.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'comentario.label', default: 'Comentario'), comentario.id])
                redirect comentario
            }
            '*' { respond comentario, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond comentarioService.get(id)
    }

    def update(Comentario comentario) {
        if (comentario == null) {
            notFound()
            return
        }

        try {
            comentarioService.save(comentario)
        } catch (ValidationException e) {
            respond comentario.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'comentario.label', default: 'Comentario'), comentario.id])
                redirect comentario
            }
            '*'{ respond comentario, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        comentarioService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'comentario.label', default: 'Comentario'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'comentario.label', default: 'Comentario'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
