package technical.test

class Moniteur {

	String nom

	String prenom

	static hasMany = [activites : Activite]

    static constraints = {
    }
}
