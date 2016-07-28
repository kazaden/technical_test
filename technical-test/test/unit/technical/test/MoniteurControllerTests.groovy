package technical.test



import org.junit.*
import grails.test.mixin.*

@TestFor(MoniteurController)
@Mock(Moniteur)
class MoniteurControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/moniteur/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.moniteurInstanceList.size() == 0
        assert model.moniteurInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.moniteurInstance != null
    }

    void testSave() {
        controller.save()

        assert model.moniteurInstance != null
        assert view == '/moniteur/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/moniteur/show/1'
        assert controller.flash.message != null
        assert Moniteur.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/moniteur/list'

        populateValidParams(params)
        def moniteur = new Moniteur(params)

        assert moniteur.save() != null

        params.id = moniteur.id

        def model = controller.show()

        assert model.moniteurInstance == moniteur
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/moniteur/list'

        populateValidParams(params)
        def moniteur = new Moniteur(params)

        assert moniteur.save() != null

        params.id = moniteur.id

        def model = controller.edit()

        assert model.moniteurInstance == moniteur
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/moniteur/list'

        response.reset()

        populateValidParams(params)
        def moniteur = new Moniteur(params)

        assert moniteur.save() != null

        // test invalid parameters in update
        params.id = moniteur.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/moniteur/edit"
        assert model.moniteurInstance != null

        moniteur.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/moniteur/show/$moniteur.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        moniteur.clearErrors()

        populateValidParams(params)
        params.id = moniteur.id
        params.version = -1
        controller.update()

        assert view == "/moniteur/edit"
        assert model.moniteurInstance != null
        assert model.moniteurInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/moniteur/list'

        response.reset()

        populateValidParams(params)
        def moniteur = new Moniteur(params)

        assert moniteur.save() != null
        assert Moniteur.count() == 1

        params.id = moniteur.id

        controller.delete()

        assert Moniteur.count() == 0
        assert Moniteur.get(moniteur.id) == null
        assert response.redirectedUrl == '/moniteur/list'
    }
}
