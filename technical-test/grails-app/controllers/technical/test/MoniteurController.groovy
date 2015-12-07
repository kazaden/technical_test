package technical.test

import org.springframework.dao.DataIntegrityViolationException

class MoniteurController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [moniteurInstanceList: Moniteur.list(params), moniteurInstanceTotal: Moniteur.count()]
    }

    def create() {
        [moniteurInstance: new Moniteur(params)]
    }

    def save() {
        def moniteurInstance = new Moniteur(params)
        if (!moniteurInstance.save(flush: true)) {
            render(view: "create", model: [moniteurInstance: moniteurInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'moniteur.label', default: 'Moniteur'), moniteurInstance.id])
        redirect(action: "show", id: moniteurInstance.id)
    }

    def show(Long id) {
        def moniteurInstance = Moniteur.get(id)
        if (!moniteurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moniteur.label', default: 'Moniteur'), id])
            redirect(action: "list")
            return
        }

        [moniteurInstance: moniteurInstance]
    }

    def edit(Long id) {
        def moniteurInstance = Moniteur.get(id)
        if (!moniteurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moniteur.label', default: 'Moniteur'), id])
            redirect(action: "list")
            return
        }

        [moniteurInstance: moniteurInstance]
    }

    def update(Long id, Long version) {
        def moniteurInstance = Moniteur.get(id)
        if (!moniteurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moniteur.label', default: 'Moniteur'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (moniteurInstance.version > version) {
                moniteurInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'moniteur.label', default: 'Moniteur')] as Object[],
                          "Another user has updated this Moniteur while you were editing")
                render(view: "edit", model: [moniteurInstance: moniteurInstance])
                return
            }
        }

        moniteurInstance.properties = params

        if (!moniteurInstance.save(flush: true)) {
            render(view: "edit", model: [moniteurInstance: moniteurInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'moniteur.label', default: 'Moniteur'), moniteurInstance.id])
        redirect(action: "show", id: moniteurInstance.id)
    }

    def delete(Long id) {
        def moniteurInstance = Moniteur.get(id)
        if (!moniteurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moniteur.label', default: 'Moniteur'), id])
            redirect(action: "list")
            return
        }

        try {
            moniteurInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'moniteur.label', default: 'Moniteur'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'moniteur.label', default: 'Moniteur'), id])
            redirect(action: "show", id: id)
        }
    }
}
