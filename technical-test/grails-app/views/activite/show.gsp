
<%@ page import="technical.test.Activite" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'activite.label', default: 'Activite')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body id="page-activite">
		<a href="#show-activite" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-activite" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list activite">
			
				<div class="">

					<div class="">

						<g:if test="${activiteInstance?.description}">
						<li class="fieldcontain">
							<span id="description-label" class="property-label"><g:message code="activite.description.label" default="Description" /></span>
							
								<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${activiteInstance}" field="description"/></span>
							
						</li>
						</g:if>
					
						<g:if test="${activiteInstance?.moniteur}">
						<li class="fieldcontain">
							<span id="moniteur-label" class="property-label"><g:message code="activite.moniteur.label" default="Moniteur" /></span>
							
								<span class="property-value" aria-labelledby="moniteur-label"><g:link controller="moniteur" action="show" id="${activiteInstance?.moniteur?.id}">${activiteInstance?.moniteur?.encodeAsHTML()}</g:link></span>
							
						</li>
						</g:if>
					
						<g:if test="${activiteInstance?.nomActivite}">
						<li class="fieldcontain">
							<span id="nomActivite-label" class="property-label"><g:message code="activite.nomActivite.label" default="Nom Activite" /></span>
							
								<span class="property-value" aria-labelledby="nomActivite-label"><g:fieldValue bean="${activiteInstance}" field="nomActivite"/></span>
							
						</li>
						</g:if>

					</div>
					<div class="">
						
					</div>

				</div>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${activiteInstance?.id}" />
					<g:link class="edit" action="edit" id="${activiteInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
