package technical.test

class Activite {

	String nomActivite

	String description

	Integer nbParticipantsMax 

	static belongsTo = [moniteur : Moniteur]

    static constraints = {

    }
}
