<%@ page import="technical.test.Activite" %>



<div class="fieldcontain ${hasErrors(bean: activiteInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="activite.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${activiteInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: activiteInstance, field: 'moniteur', 'error')} required">
	<label for="moniteur">
		<g:message code="activite.moniteur.label" default="Moniteur" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="moniteur" name="moniteur.id" from="${technical.test.Moniteur.list()}" optionKey="id" required="" value="${activiteInstance?.moniteur?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: activiteInstance, field: 'nomActivite', 'error')} ">
	<label for="nomActivite">
		<g:message code="activite.nomActivite.label" default="Nom Activite" />
		
	</label>
	<g:textField name="nomActivite" value="${activiteInstance?.nomActivite}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: activiteInstance, field: 'nbParticipantsMax', 'error')} ">
	<label for="nbParticipantsMax">
		<g:message code="activite.nbParticipantsMax.label" default="Nombre max de participants" />
		
	</label>
	<g:textField name="nbParticipantsMax" value="${activiteInstance?.nbParticipantsMax}"/>
</div>

