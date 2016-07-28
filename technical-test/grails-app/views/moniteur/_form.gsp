<%@ page import="technical.test.Moniteur" %>



<div class="fieldcontain ${hasErrors(bean: moniteurInstance, field: 'activites', 'error')} ">
	<label for="activites">
		<g:message code="moniteur.activites.label" default="Activites" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${moniteurInstance?.activites?}" var="a">
    <li><g:link controller="activite" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="activite" action="create" params="['moniteur.id': moniteurInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'activite.label', default: 'Activite')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: moniteurInstance, field: 'nom', 'error')} ">
	<label for="nom">
		<g:message code="moniteur.nom.label" default="Nom" />
		
	</label>
	<g:textField name="nom" value="${moniteurInstance?.nom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: moniteurInstance, field: 'prenom', 'error')} ">
	<label for="prenom">
		<g:message code="moniteur.prenom.label" default="Prenom" />
		
	</label>
	<g:textField name="prenom" value="${moniteurInstance?.prenom}"/>
</div>

