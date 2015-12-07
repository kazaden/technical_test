package technical.test

class Activite {

	String nomActivite

	String description

	static belongsTo = [moniteur : Moniteur]

    static constraints = {
    }
}
