package technical.test

import org.springframework.dao.DataIntegrityViolationException

class ActiviteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [activiteInstanceList: Activite.list(params), activiteInstanceTotal: Activite.count()]
    }

    def create() {
        [activiteInstance: new Activite(params)]
    }

    def save() {
        def activiteInstance = new Activite(params)
        if (!activiteInstance.save(flush: true)) {
            render(view: "create", model: [activiteInstance: activiteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'activite.label', default: 'Activite'), activiteInstance.id])
        redirect(action: "show", id: activiteInstance.id)
    }

    def show(Long id) {
        def activiteInstance = Activite.get(id)
        if (!activiteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'activite.label', default: 'Activite'), id])
            redirect(action: "list")
            return
        }

        [activiteInstance: activiteInstance]
    }

    def edit(Long id) {
        def activiteInstance = Activite.get(id)
        if (!activiteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'activite.label', default: 'Activite'), id])
            redirect(action: "list")
            return
        }

        [activiteInstance: activiteInstance]
    }

    def update(Long id, Long version) {
        def activiteInstance = Activite.get(id)
        if (!activiteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'activite.label', default: 'Activite'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (activiteInstance.version > version) {
                activiteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'activite.label', default: 'Activite')] as Object[],
                          "Another user has updated this Activite while you were editing")
                render(view: "edit", model: [activiteInstance: activiteInstance])
                return
            }
        }

        activiteInstance.properties = params

        if (!activiteInstance.save(flush: true)) {
            render(view: "edit", model: [activiteInstance: activiteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'activite.label', default: 'Activite'), activiteInstance.id])
        redirect(action: "show", id: activiteInstance.id)
    }

    def delete(Long id) {
        def activiteInstance = Activite.get(id)
        if (!activiteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'activite.label', default: 'Activite'), id])
            redirect(action: "list")
            return
        }

        try {
            activiteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'activite.label', default: 'Activite'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'activite.label', default: 'Activite'), id])
            redirect(action: "show", id: id)
        }
    }
}
