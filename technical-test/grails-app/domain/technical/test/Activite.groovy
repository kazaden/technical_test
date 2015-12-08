package technical.test

class Activite {

	String nomActivite

	String description

	static belongsTo = [moniteur : Moniteur]

	Integer nbParticipantsMax 

    static constraints = {
    	nbParticipantsMax blank : true, nullable: true, min: 1, max: 8
    }
}
