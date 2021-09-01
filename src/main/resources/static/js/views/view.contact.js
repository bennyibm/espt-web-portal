/*
Name: 			View - Contact
Written by: 	Okler Themes - (http://www.okler.net)
Theme Version:	9.0.0
*/

(function($) {

	'use strict';

	/*
	Custom Rules
	*/

	// No White Space
	$.validator.addMethod("noSpace", function (value, element) {
		if ($(element).attr('required')) {
			return value.search(/^(?! *$)[^]+$/) == 0;
		}

		return true;
	}, 'Ce champ est obligatoire');

	/*
	Assign Custom Rules on Fields
	*/
	$.validator.addClassRules({
		'form-control': {
			noSpace: true
		}
	});

	/*
	Contact Form: Basic
	*/
	$('.contact-form').each(function () {
		$(this).validate({
			errorPlacement: (error, element) => {
				if (element.attr('type') == 'radio' || element.attr('type') == 'checkbox') {
					error.appendTo(element.closest('.form-group'));
				} else if (element.is('select') && element.closest('.custom-select-1')) {
					error.appendTo(element.closest('.form-group'));
				} else {
					if (element.closest('.form-group').length) {
						error.appendTo(element.closest('.form-group'));
					} else {
						error.insertAfter(element);
					}
				}
			},
			submitHandler: (form) => {
				console.log("all fields are valid, now submitting data to the portal...");
				const docRef = form.getElementById("refDocument").value;
				const studentName = form.getElementById("studentName").value;

				const authenticateDocumentUrl = "/authenticate-document";

				const authenticateDocumentRequestDto = {
					docRef : docRef,
					studentName : studentName.value
				}

				fetch(authenticateDocumentUrl, {
					method: "POST",
					headers: {
						"Content-Type": "application/json"
					},
					body: JSON.stringify(authenticateDocumentRequestDto)}
				)
					.then(response => {
						console.log("transforming the response from the portal in a json format");
						return response.json()
					})
					.then(response => {
						console.log("response from portal : ", response);
						if(response.error){
							//error on in the response retrieved from portal
							displayErrorMessage(response.message)
						}
					})
					.catch( (error) => displayErrorMessage())

			}
		})

	}).apply(this, [jQuery]);
})
